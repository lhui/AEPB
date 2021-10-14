package com.example.AEPB;

public class AirCoinAmountBeyondLimitException extends RuntimeException {
    public AirCoinAmountBeyondLimitException(String message) {
        super(message);
    }
}
