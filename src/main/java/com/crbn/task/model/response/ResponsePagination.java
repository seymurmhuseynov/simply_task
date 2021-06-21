package com.crbn.task.model.response;


public class ResponsePagination {
    private Long total;
    private long pageSize;
    private Object dataList;

    public ResponsePagination() {
    }

    public Long getTotal() {
        return total;
    }

    public ResponsePagination setTotal(Long total) {
        this.total = total;
        return this;
    }

    public long getPageSize() {
        return pageSize;
    }

    public ResponsePagination setPageSize(long pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Object getDataList() {
        return dataList;
    }

    public ResponsePagination setDataList(Object dataList) {
        this.dataList = dataList;
        return this;
    }
}
