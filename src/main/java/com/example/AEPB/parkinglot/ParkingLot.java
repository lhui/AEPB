package com.example.AEPB.parkinglot;

import com.example.AEPB.parkinglot.exception.CanNotGetTicketException;
import com.example.AEPB.parkinglot.exception.CarNotFoundException;
import com.example.AEPB.parkinglot.exception.NullTicketCanNotGetCarException;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class ParkingLot {
    private Map<Ticket, Car> parkingLotMap = new HashMap<>();
    private static final int MAX_PARKING_COUNT = 50;


    public Ticket parkingCarAndGetTicket(Car car) {
        if (isNull(car)) {
            throw new CanNotGetTicketException("You need at least one car to get a ticket.");
        }
        if (parkingLotMap.size() == MAX_PARKING_COUNT) {
            throw new ParkingLotIsFullException("The parkingLot is full, can not parking any car.");
        }
        Ticket ticket = new Ticket();
        parkingLotMap.put(ticket, car);
        return ticket;
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
