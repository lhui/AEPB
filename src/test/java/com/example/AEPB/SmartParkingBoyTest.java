package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.samrtparkingboy.SmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class SmartParkingBoyTest {
    /*
     * given 1号停车场停有1辆车，2号停车场停有2两辆车，其他停车场为空，smart Parking boy，一辆车
     * when 停车
     * then 停车成功并获得车票，停在了3号停车位
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_no1_parking_lot_has_one_car_and_no2_parking_lot_has_two_car_and_other_parking_lot_no_car_and_smart_parking_boy_and_one_car(){
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        parkingLotMap.get("1").parkingCarAndGetTicket(new Car());
        parkingLotMap.get("2").parkingCarAndGetTicket(new Car());
        parkingLotMap.get("2").parkingCarAndGetTicket(new Car());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotMap);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.parkingCar(car);
        Assertions.assertEquals(car, parkingLotMap.get("3").getCar(ticket));
    }

    /*
     * given 所有停车场都有0辆车，smart boy，一辆车
     * when 停车
     * then  停车成功并获得车票，停在了1号停车位
     * */

    /*
     * given 所有停车场都有满，smart boy，一辆车
     * when 停车
     * then  停车失败并抛异常
     * */

    private Map<String, ParkingLot> creatParkingLots() {
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        IntStream.rangeClosed(1, 10).forEach(parkingLotNumber -> {
            ParkingLot parkingLot = new ParkingLot();
            parkingLotMap.put(String.valueOf(parkingLotNumber), parkingLot);
        });
        return parkingLotMap;
    }
}
