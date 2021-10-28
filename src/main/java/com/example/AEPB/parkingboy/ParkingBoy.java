package com.example.AEPB.parkingboy;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;

import java.util.Map;

public class ParkingBoy {
    private Map<String, ParkingLot> parkingLotMap;

    public ParkingBoy(Map<String, ParkingLot> parkingLotMap) {
        this.parkingLotMap = parkingLotMap;
    }

    public Car pickUpCar(Ticket ticket) {
        for (int parkingLotNumber = 1; parkingLotNumber <= 10; parkingLotNumber++) {
            ParkingLot parkingLot = parkingLotMap.get(String.valueOf(parkingLotNumber));
            Car car = parkingLot.getCar(ticket);
            parkingLotMap.put(String.valueOf(parkingLotNumber), parkingLot);
            return car;
        }
        return null;
    }

    public Ticket parkingCar(Car car) {
        Ticket ticket;
        for (int parkingLotNumber = 1; parkingLotNumber <= 10; parkingLotNumber++) {
            ParkingLot parkingLot = parkingLotMap.get(String.valueOf(parkingLotNumber));
            if (!parkingLot.checkParkingLotIsFull()){
                ticket = parkingLot.parkingCarAndGetTicket(car);
                parkingLotMap.put(String.valueOf(parkingLotNumber), parkingLot);
                return ticket;
            }
        }
        throw new ParkingLotIsFullException("parkingLots is full");
    }


}
