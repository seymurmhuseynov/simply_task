package com.crbn.task.daos;

import com.crbn.task.entities.Customers;
import com.crbn.task.entities.OrderDetails;
import com.crbn.task.entities.Orders;
import com.crbn.task.entities.view.ViewOrderDetails;
import com.crbn.task.entities.view.ViewOrders;
import com.crbn.task.enums.EnumExceptionsMessage;
import com.crbn.task.enums.EnumOrderStatusMessage;
import com.crbn.task.exceptions.NotFoundException;
import com.crbn.task.model.response.ResponseOrders;
import com.crbn.task.model.request.RequestBetweenDate;
import com.crbn.task.model.response.Response;
import com.crbn.task.model.request.RequestOrder;
import com.crbn.task.repos.*;
import com.crbn.task.services.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DaoOrders {

    @Autowired
    RepoOrders repoOrders;

    @Autowired
    RepoViewOrders repoViewOrders;

    @Autowired
    RepoOrderDetails repoOrderDetails;

    @Autowired
    RepoViewOrderDetails repoViewOrderDetails;

    @Autowired
    RepoCustomers repoCustomers;

    @Autowired
    CustomerDetailsService customerDetailsService;

    public Response insert(RequestOrder requestOrder) {
        Orders order = new Orders();
        repoOrders.save(order
                .setIdAddress(requestOrder.getIdAddress())
                .setStatus(EnumOrderStatusMessage.IN_PROCESS.getStatus())
                .setCreatedDate(LocalDateTime.now())
        );
        for (Long idProduct : requestOrder.getIdProducts()) {
            repoOrderDetails.save(new OrderDetails()
                    .setIdOrder(order.getId())
                    .setIdProduct(idProduct)
            );
        }
        return new Response();
    }

    public Response cancelled(long idOrder) {
        Optional<Orders> order = repoOrders.findById(idOrder);
        if (order.isPresent()) {
            repoOrders.save(order.get().setStatus(EnumOrderStatusMessage.CANCELLED.getStatus()));
            return new Response();
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response changeStatus(long idOrder) {
        double totalPrice = repoOrderDetails.sumPriceByOrder(idOrder);
        Optional<ViewOrders> viewOrder = repoViewOrders.findById(idOrder);
        if (viewOrder.isPresent()) {
            if (Math.abs(viewOrder.get().getBalance()) - Math.abs(totalPrice) < 0) {
                throw new NotFoundException("you do not have enough money in your balance.");
            } else {
                Optional<Customers> customer = repoCustomers.findById(viewOrder.get().getIdCustomer());
                customer.ifPresent(customers -> repoCustomers.save(
                        customers.setBalance(Math.abs(viewOrder.get().getBalance()) - Math.abs(totalPrice)))
                );
            }
        }

        Optional<Orders> order = repoOrders.findById(idOrder);
        order.ifPresent(value -> repoOrders.save(
                value.setFinishedDate(LocalDateTime.now())
                        .setStatus(EnumOrderStatusMessage.FINISHED.getStatus()))
        );
        return new Response();
    }

    public Response selectOrders(long idOrder) {
        Optional<ViewOrders> orders = repoViewOrders.findById(idOrder);
        if (orders.isPresent()) {
            List<ViewOrderDetails> orderDetails = repoViewOrderDetails.findAllByIdOrder(idOrder);
            return new Response().setResponse(
                    new ResponseOrders()
                            .setOrders(orders.get())
                            .setOrderDetails(orderDetails)
            );
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectMyOrders() {
        List<ResponseOrders> responseOrders = new ArrayList<>();

        Customers customers = customerDetailsService.localSelectUsers();
        List<ViewOrders> orders = repoViewOrders.findAllByIdCustomer(customers.getId());
        if (orders != null && orders.size() > 0) {
            for (ViewOrders order : orders) {
                List<ViewOrderDetails> orderDetails = repoViewOrderDetails.findAllByIdOrder(order.getId());
                responseOrders.add(
                        new ResponseOrders()
                                .setOrders(order)
                                .setOrderDetails(orderDetails)
                );
            }
            return new Response().setResponse(responseOrders);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response selectStatus(int status) {
        List<ViewOrders> viewOrders = repoViewOrders.findAllByStatus(status);
        if (viewOrders != null && viewOrders.size() > 0) {
            return new Response().setResponse(viewOrders);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

    public Response finishedOrders(RequestBetweenDate requestBetweenDate) {
        List<ViewOrders> viewOrders = repoViewOrders.findAllByFinishedDateBetweenAndStatus(
                requestBetweenDate.getStartDate(),
                requestBetweenDate.getFinishedDate(),
                EnumOrderStatusMessage.FINISHED.getStatus()
        );
        if (viewOrders != null && viewOrders.size() > 0) {
            return new Response().setResponse(viewOrders);
        } else {
            throw new NotFoundException(EnumExceptionsMessage.DATA_NOT_FOUND.getMessage());
        }
    }

}
