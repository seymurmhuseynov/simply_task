package com.crbn.task.enums;

public enum EnumOrderStatusMessage {

    IN_PROCESS(1),
    CANCELLED(2),
    FINISHED(3);

    private final int status;

    EnumOrderStatusMessage(final int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
