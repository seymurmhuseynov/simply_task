package com.crbn.task.model.response;

import com.crbn.task.entities.view.ViewOrderDetails;
import com.crbn.task.entities.view.ViewOrders;

import java.util.List;

public class ResponseOrders {

    public ViewOrders orders;
    public List<ViewOrderDetails> orderDetails;

    public ResponseOrders() {
    }

    public ViewOrders getOrders() {
        return orders;
    }

    public ResponseOrders setOrders(ViewOrders orders) {
        this.orders = orders;
        return this;
    }

    public List<ViewOrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public ResponseOrders setOrderDetails(List<ViewOrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }
}
