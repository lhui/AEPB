package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class ParkingLotGroup {
    private Map<Integer, ParkingLot> parkingLotMap = new HashMap<>();

    ParkingLotGroup() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ParkingLot parkingLot = new ParkingLot();
            parkingLotMap.put(i, parkingLot);
        });
    }

    public Ticket parkingCarAndGetTicketByParkingBoy(Car car) {
        for (int i = 1; i <= 10; i++) {
            try {
                ParkingLot parkingLot = parkingLotMap.get(i);
                Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
                parkingLotMap.put(i, parkingLot);
                return ticket;
            } catch (Exception e) {
            }
        }
        throw new ParkingLotGroupIsFullException("there is no place for parking a car.");
    }

    public Car getCarByTicketAndSelfParking(Ticket ticket, int i) {
        ParkingLot parkingLot = parkingLotMap.get(i);
        return parkingLot.getCar(ticket);
    }

    public Ticket parkingCarAndGetTicketBySelfParking(Car car, int i) {
        ParkingLot parkingLot = parkingLotMap.get(i);
        return parkingLot.parkingCarAndGetTicket(car);
    }
}
