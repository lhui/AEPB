package com.example.AEPB.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private Map<Ticket, Car> parkingLotMap = new HashMap<>();
    private static final int MAX_PARKING_COUNT = 50;
    

    public Ticket parkingCarAndGetTicket(Car car) {
        if (parkingLotMap.size() == MAX_PARKING_COUNT) {
            return null;
        }
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        parkingLotMap.put(ticket, car);
        return ticket;
    }

    public int getTicketCount() {
        return parkingLotMap.size();
    }

    public int getParkingLotSpace() {
        return MAX_PARKING_COUNT - getTicketCount();
    }

    public Car getCar(Ticket ticket) {
        if (!parkingLotMap.containsKey(ticket)) {
            throw new CarNotFoundException("the Car can not be found in the parkingLot");
        }
        Car car = parkingLotMap.get(ticket);
        parkingLotMap.remove(ticket);
        return car;

    }
}
