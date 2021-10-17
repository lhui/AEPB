package com.example.AEPB.parkinglot;

import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private Map<Ticket, Car> parkingLot;
    private static final int MAX_PARKING_COUNT = 50;

    public ParkingLot(Map<Ticket, Car> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket parkingCarAndGetTicket(Car car) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        parkingLot.put(ticket, car);
        return ticket;
    }

    public int getTicketCount() {
        return parkingLot.size();
    }

    public int getParkingLotSpace() {
        return MAX_PARKING_COUNT - getTicketCount();
    }
}
