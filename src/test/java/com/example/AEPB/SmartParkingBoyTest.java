package com.example.AEPB;

import com.example.AEPB.parkingboy.ParkingBoy;
import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.NullTicketCanNotGetCarException;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;
import com.example.AEPB.samrtparkingboy.SmartParkingBoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class SmartParkingBoyTest {
    /*
     * 1.
     * given 1号停车场停有1辆车，2号停车场停有2两辆车，其他停车场为空，SmartParkingBoy，一辆车
     * when 停车
     * then 停车成功并获得车票，停在了3号停车位
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_no1_parking_lot_has_one_car_and_no2_parking_lot_has_two_car_and_other_parking_lot_no_car_and_smart_parking_boy_and_one_car() {
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
     * 2.
     * given 所有停车场都有0辆车，SmartParkingBoy，一辆车
     * when 停车
     * then  停车成功并获得车票，停在了1号停车位
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_all_empty_parking_lot_and_smart_parking_boy_and_park_one_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotMap);
        Car car = new Car();
        Ticket ticket = smartParkingBoy.parkingCar(car);
        Assertions.assertEquals(car, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 3.
     * given 所有停车场都有满，SmartParkingBoy，一辆车
     * when 停车
     * then  停车失败并抛异常
     * */
    @Test
    void should_park_car_fail_when_parking_car_given_all_full_parking_lot_and_smart_boy_and_park_one_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        ParkingBoy parkingLot = new ParkingBoy(parkingLotMap);
        for (int parkingCarCount = 1; parkingCarCount <= 500; parkingCarCount++) {
            parkingLot.parkingCar(new Car());
        }
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotMap);
        Car car = new Car();
        Assertions.assertThrows(ParkingLotIsFullException.class, () -> smartParkingBoy.parkingCar(car));
    }

    /*
     * 4、
     * given 1号停车场停有一辆车，给SmartParkingBoy对应票
     * when 取车
     * then 取车成功
     * */
    @Test
    void should_get_car_successfully_when_take_the_car_given_No1_parkingLot_has_one_car_and_smart_parking_boy_and_one_matched_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(car);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotMap);
        Assertions.assertEquals(car, smartParkingBoy.pickUpCar(ticket));
    }

    /*
     * 5、
     * given 1号停车场停有一辆车，给SmartParkingBoy无效票
     * when 取车
     * then 取车失败并抛异常
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_smart_parking_boy_and_one_invalid_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        parkingLotMap.get("1").parkingCarAndGetTicket(car);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotMap);
        Assertions.assertThrows(NullTicketCanNotGetCarException.class, () -> smartParkingBoy.pickUpCar(null));
    }

    private Map<String, ParkingLot> creatParkingLots() {
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        IntStream.rangeClosed(1, 10).forEach(parkingLotNumber -> {
            ParkingLot parkingLot = new ParkingLot();
            parkingLotMap.put(String.valueOf(parkingLotNumber), parkingLot);
        });
        return parkingLotMap;
    }
}
