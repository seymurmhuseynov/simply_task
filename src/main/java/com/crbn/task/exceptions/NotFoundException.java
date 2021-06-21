package com.crbn.task.exceptions;

/**
 * @author : Pallas
 * @project : Empati
 * @created on : 31-August-2020
 * @email : talehji@gmail.com
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
