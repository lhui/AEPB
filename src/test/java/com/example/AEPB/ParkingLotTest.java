package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.CanNotGetTicketException;
import com.example.AEPB.parkinglot.exception.CarNotFoundException;
import com.example.AEPB.parkinglot.exception.NullTicketCanNotGetCarException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * given 一个空的停车场和一辆想要存的车
 * when 存车
 * then 存车成功并拿到存车票
 *
 * given 一个存有想要取的车的停车场和对应的车票
 * when 取车
 * then 取车成功并拿到车
 *
 * given 一个空的停车场和一张取过车的车票
 * when 取车
 * then 取车失败抛出异常
 *
 * given 一个停有1辆车的停车场和一张没有对应车辆的车票
 * when 取车
 * then 取车失败抛出异常
 *
 * given 一个存有50辆车的停车场和一辆想要存的车
 * when 存车
 * then 存车失败没有车票
 *
 * given 一个存有49辆车的停车场和一辆想要存的车
 * when 存车
 * then 存车成功并拿到存车票
 *
 * given 一个空的停车场和我没有车
 * when 存车
 * then 存车失败抛出异常
 *
 * given 一个停有1辆车的停车场和我没票
 * when 取车
 * then 取车失败抛出异常
 *
 * */
class ParkingLotTest {
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_empty_parkingLot_and_one_parking_car() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
        Assertions.assertEquals(car, parkingLot.getCar(ticket));
    }

    @Test
    void should_get_car_successfully_when_take_the_car_given_parkingLot_has_one_car_and_one_matched_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
        Car parkingLotCar = parkingLot.getCar(ticket);
        Assertions.assertEquals(car, parkingLotCar);
    }

    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_give_empty_parkingLot_and_one_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
        parkingLot.getCar(ticket);
        Assertions.assertThrows(CarNotFoundException.class, () -> parkingLot.getCar(ticket));
    }

    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_give_one_car_parkingLot_and_one_not_exist_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.parkingCarAndGetTicket(car);
        Ticket ticket = new Ticket();
        Assertions.assertThrows(CarNotFoundException.class, () -> parkingLot.getCar(ticket));
    }

    @Test
    void should_get_null_ticket_when_parking_car_given_parkingLot_is_full_and_one_parking_car() {
        ParkingLot parkingLot = setupFullParkingLot();
        Car car = new Car();
        Assertions.assertNull(parkingLot.parkingCarAndGetTicket(car));
    }

    private ParkingLot setupFullParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 1; i <= 50; i++) {
            Car car = new Car();
            parkingLot.parkingCarAndGetTicket(car);
        }
        return parkingLot;
    }

    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_one_parkingLot_which_has_49_car_and_one_parking_car() {
        ParkingLot parkingLot = setup49ParkingLot();
        Car car = new Car();
        parkingLot.parkingCarAndGetTicket(car);
    }

    private ParkingLot setup49ParkingLot() {
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 1; i <= 49; i++) {
            Car car = new Car();
            parkingLot.parkingCarAndGetTicket(car);
        }
        return parkingLot;
    }

    @Test
    void should_park_car_and_get_ticket_failed_throw_exception_when_parking_car_given_one_empty_parkingLot_and_no_parking_car() {
        ParkingLot parkingLot = new ParkingLot();
        Assertions.assertThrows(CanNotGetTicketException.class, () -> parkingLot.parkingCarAndGetTicket(null));
    }

    @Test
    void should_get_car_failed_when_take_the_car_given_one_car_parkingLot_and_no_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.parkingCarAndGetTicket(car);
        Assertions.assertThrows(NullTicketCanNotGetCarException.class, () -> parkingLot.getCar(null));
    }

}
