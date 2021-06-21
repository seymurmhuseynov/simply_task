package com.crbn.task.model.request;

import java.time.LocalDateTime;

public class RequestBetweenDate {

    private LocalDateTime startDate;
    private LocalDateTime finishedDate;

    public RequestBetweenDate() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public RequestBetweenDate setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getFinishedDate() {
        return finishedDate;
    }

    public RequestBetweenDate setFinishedDate(LocalDateTime finishedDate) {
        this.finishedDate = finishedDate;
        return this;
    }
}
