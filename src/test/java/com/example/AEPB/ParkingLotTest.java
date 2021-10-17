package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * given 一个空的停车场和一辆想要存的车
 * when 存车
 * then 存车成功并拿到存车票
 *
 *
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
        Map<Ticket, Car> parkingLotMap = new HashMap<>();
        ParkingLot parkingLot = new ParkingLot(parkingLotMap);
        Car car = new Car("vin");
        Ticket ticket = parkingLot.parkingCarAndGetTicket(car);
        Assertions.assertEquals(1, parkingLot.getTicketCount());
        Assertions.assertEquals(49, parkingLot.getParkingLotSpace());
    }

}
