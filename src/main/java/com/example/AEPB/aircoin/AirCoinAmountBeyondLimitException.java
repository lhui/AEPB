package com.example.AEPB.aircoin;

public class AirCoinAmountBeyondLimitException extends RuntimeException {
    public AirCoinAmountBeyondLimitException(String message) {
        super(message);
    }
}
