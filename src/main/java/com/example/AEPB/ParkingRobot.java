package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;
import com.example.AEPB.samrtparkingboy.SmartParkingBoy;

import java.util.Map;

public class ParkingRobot extends SmartParkingBoy {
    private Map<String, ParkingLot> parkingLotMap;

    public ParkingRobot() {
        super();
    }

    public ParkingRobot(Map<String, ParkingLot> parkingLotMap) {
        this.parkingLotMap = parkingLotMap;
    }

    @Override
    public Car pickUpCar(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket parkingCar(Car car) {
        int mostParkingLotNumber = getTheBiggestVacancyRateParkingLot();
        if (parkingLotMap.get(String.valueOf(mostParkingLotNumber)).checkParkingLotIsFull()) {
            throw new ParkingLotIsFullException("The parkingLot is full, can not parking any car.");
        }
        ParkingLot parkingLot = parkingLotMap.get(String.valueOf(mostParkingLotNumber));
        return parkingLot.parkingCarAndGetTicket(car);
    }

    private int getTheBiggestVacancyRateParkingLot() {
        int biggestVacancyRateParkingLotNumber = 1;
        for (int parkingLotNumber = 2; parkingLotNumber <= parkingLotMap.size(); parkingLotNumber++) {
            ParkingLot biggestParkingLot = parkingLotMap.get(String.valueOf(biggestVacancyRateParkingLotNumber));
            ParkingLot comparedParkingLot = parkingLotMap.get(String.valueOf(parkingLotNumber));

            if (calculateTheVacancyRate(biggestParkingLot) < (calculateTheVacancyRate(comparedParkingLot))) {
                biggestVacancyRateParkingLotNumber = parkingLotNumber;
            }
        }
        return biggestVacancyRateParkingLotNumber;
    }

    private double calculateTheVacancyRate(ParkingLot biggestParkingLot) {
        return (double) (biggestParkingLot.getParkingLotRemainCount()) / (double) biggestParkingLot.getMaxParkingCount();
    }


}
