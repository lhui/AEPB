package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AdvancedParkingLotTest {
    /*
     * 1、
     * given 10个停车场都未停车，停车小子，一辆车
     * when 停车
     * then 停车成功并获得车票
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_ten_empty_parkingLots_and_parking_boy_and_one_parking_car() {
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        Car car = new Car();
        Ticket ticket = parkingLotGroup.parkingCarAndGetTicketByParkingBoy(car);
        Assertions.assertEquals(car, parkingLotGroup.getCarByTicketAndParkingBoy(ticket));
    }

    /*
     * 2、
     * given 10个停车场都未停车，自停1号停车场，一辆车
     * when 停车
     * then 停车成功并获得车票
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_ten_empty_parkingLots_and_self_parking_No1_parkingLot_and_one_parking_car() {
        ParkingLotGroup parkingLotGroup = new ParkingLotGroup();
        Car car = new Car();
        Ticket ticket = parkingLotGroup.parkingCarAndGetTicketBySelfParking(car, 1);
        Assertions.assertEquals(car, parkingLotGroup.getCarByTicketAndParkingBoy(ticket));
    }
}
