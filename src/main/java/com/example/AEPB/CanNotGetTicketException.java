package com.example.AEPB;

public class CanNotGetTicketException extends RuntimeException {
    public CanNotGetTicketException(String message) {
        super(message);
    }
}
