package com.example.AEPB.parkinglot;

import com.example.AEPB.CanNotGetTicketException;
import com.example.AEPB.NullTicketCanNotGetCarException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;

public class ParkingLot {
    private Map<Ticket, Car> parkingLotMap = new HashMap<>();
    private static final int MAX_PARKING_COUNT = 50;


    public Ticket parkingCarAndGetTicket(Car car) {
        if (isNull(car)) {
            throw new CanNotGetTicketException("You need at least one car to get a ticket.");
        }
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
        if (isNull(ticket)) {
            throw new NullTicketCanNotGetCarException("You need at least one ticket to get a car.");
        }
        if (!parkingLotMap.containsKey(ticket)) {
            throw new CarNotFoundException("The car can not be found in the parkingLot.");
        }
        Car car = parkingLotMap.get(ticket);
        parkingLotMap.remove(ticket);
        return car;

    }
}
