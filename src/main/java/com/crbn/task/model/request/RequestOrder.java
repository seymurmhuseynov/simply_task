package com.crbn.task.model.request;

import java.util.List;

public class RequestOrder {
    private long idAddress;
    private List<Long> idProducts;

    public RequestOrder() {
    }

    public long getIdAddress() {
        return idAddress;
    }

    public RequestOrder setIdAddress(long idAddress) {
        this.idAddress = idAddress;
        return this;
    }

    public List<Long> getIdProducts() {
        return idProducts;
    }

    public RequestOrder setIdProducts(List<Long> idProducts) {
        this.idProducts = idProducts;
        return this;
    }
}
