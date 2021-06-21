package com.crbn.task.enums;

public enum EnumExceptionsMessage {

    DATA_ALREADY_EXIST("Data Already Exist"),
    DATA_NOT_FOUND("Data not found"),
    USER_WRONG_USERNAME_OR_PASSWORD("Username or Password Wrong");

    private final String message;

    EnumExceptionsMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
