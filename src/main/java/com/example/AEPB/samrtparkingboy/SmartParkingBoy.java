package com.example.AEPB.samrtparkingboy;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;

import java.util.Map;

public class SmartParkingBoy {
    private Map<String, ParkingLot> parkingLotMap;

    public SmartParkingBoy(Map<String, ParkingLot> parkingLotMap) {
        this.parkingLotMap = parkingLotMap;
    }

    public SmartParkingBoy() {
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
        int mostParkingLotNumber = getTheMostAvailableParkingLot();
        if (parkingLotMap.get(String.valueOf(mostParkingLotNumber)).checkParkingLotIsFull()) {
            throw new ParkingLotIsFullException("The parkingLot is full, can not parking any car.");
        }
        ParkingLot parkingLot = parkingLotMap.get(String.valueOf(mostParkingLotNumber));
        Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
        return ticket;
    }

    private int getTheMostAvailableParkingLot() {
        int mostParkingNumber = 1;
        for (int parkingLotNumber = 2; parkingLotNumber <= 10; parkingLotNumber++) {
            if (parkingLotMap.get(String.valueOf(mostParkingNumber)).getParkingLotRemainCount()
                    < parkingLotMap.get(String.valueOf(parkingLotNumber)).getParkingLotRemainCount()) {
                mostParkingNumber = parkingLotNumber;
            }
        }
        return mostParkingNumber;
    }

}
