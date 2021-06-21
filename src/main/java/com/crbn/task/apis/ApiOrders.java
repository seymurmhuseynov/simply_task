package com.crbn.task.apis;

import com.crbn.task.daos.DaoOrders;
import com.crbn.task.model.request.RequestBetweenDate;
import com.crbn.task.model.response.Response;
import com.crbn.task.model.request.RequestOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/task/orders")
public class ApiOrders {

    @Autowired
    DaoOrders daoOrders;

    @PostMapping(value = "/insert", produces = "application/json")
    public Response insert(@RequestBody @Valid RequestOrder requestOrder) {
        return daoOrders.insert(requestOrder);
    }

    @GetMapping(value = "/cancelled", produces = "application/json")
    public Response cancelled(@RequestParam @Min(1) long idOrder) {
        return daoOrders.cancelled(idOrder);
    }

    @GetMapping(value = "/changeStatus", produces = "application/json")
    public Response changeStatus(@RequestParam @Min(1) long idOrder) {
        return daoOrders.changeStatus(idOrder);
    }

    @GetMapping(value = "/selectOrders", produces = "application/json")
    public Response selectOrders(@RequestParam @Min(1) long idOrder) {
        return daoOrders.selectOrders(idOrder);
    }

    @GetMapping(value = "/selectMyOrders", produces = "application/json")
    public Response selectMyOrders() {
        return daoOrders.selectMyOrders();
    }

    @GetMapping(value = "/selectStatus", produces = "application/json")
    public Response selectStatus(@RequestParam @Min(1) int status) {
        return daoOrders.selectStatus(status);
    }

    @PostMapping(value = "/selectFinishedOrders", produces = "application/json")
    public Response selectStatus(@RequestBody @Valid RequestBetweenDate requestBetweenDate) {
        return daoOrders.finishedOrders(requestBetweenDate);
    }

}
