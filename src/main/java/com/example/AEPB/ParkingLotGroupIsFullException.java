package com.example.AEPB;

public class ParkingLotGroupIsFullException extends RuntimeException {
    public ParkingLotGroupIsFullException(String message) {
        super(message);
    }
}
