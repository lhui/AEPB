package com.example.AEPB.parkinglot.exception;

public class CanNotGetTicketException extends RuntimeException {
    public CanNotGetTicketException(String message) {
        super(message);
    }
}
