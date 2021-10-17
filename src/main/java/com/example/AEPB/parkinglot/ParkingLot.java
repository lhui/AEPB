package com.example.AEPB.parkinglot;

import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> parkingLot;

    public ParkingLot(Map<Ticket, Car> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkingCar(Car car){
        return new Ticket();
    }
}
