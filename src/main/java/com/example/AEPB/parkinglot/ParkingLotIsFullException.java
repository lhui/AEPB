package com.example.AEPB.parkinglot;

public class ParkingLotIsFullException extends RuntimeException {
    public ParkingLotIsFullException(String message) {
        super(message);
    }
}
