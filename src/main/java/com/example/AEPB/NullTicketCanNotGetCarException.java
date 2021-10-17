package com.example.AEPB;

public class NullTicketCanNotGetCarException extends RuntimeException {
    public NullTicketCanNotGetCarException(String message) {
        super(message);
    }
}
