package com.example.AEPB.parkinglot.exception;

public class ParkingLotIsFullException extends RuntimeException {
    public ParkingLotIsFullException(String message) {
        super(message);
    }
}
