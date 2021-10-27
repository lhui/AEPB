package com.example.AEPB;

import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.Ticket;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;
import com.example.AEPB.parkingrobot.DisabledPikeUpCarException;
import com.example.AEPB.parkingrobot.ParkingRobot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ParkingRobotTest {
    /*
     * 1.
     * given 两个停车场，1号停车场10个停车位停有1辆车，2号停车场30个停车位停有2两辆车，ParkingRobot，一辆车
     * when 停车
     * then 停车成功并获得车票，停在了2号停车位
     * */
    @Test
    void should_park_car_at_no2_parking_lot_and_get_ticket_successfully_when_one_car_parked_at_no1_parking_lot_which_has_10_parking_space_and_two_cars_parked_at_no2_parking_lot_which_has_30_parking_space_and_parking_robot_and_one_car() {
        ParkingLot smallerVacancyRateParkingLot = new ParkingLot(10);
        ParkingLot biggestVacancyRateParkingLot = new ParkingLot(30);
        smallerVacancyRateParkingLot.parkingCarAndGetTicket(new Car());
        biggestVacancyRateParkingLot.parkingCarAndGetTicket(new Car());
        biggestVacancyRateParkingLot.parkingCarAndGetTicket(new Car());
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put("1", smallerVacancyRateParkingLot);
        parkingLotMap.put("2", biggestVacancyRateParkingLot);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLotMap);
        Car newCar = new Car();
        Ticket ticket = parkingRobot.parkingCar(newCar);
        Assertions.assertEquals(newCar, parkingLotMap.get("2").getCar(ticket));
    }

    /*
     * 2.
     * given 两个个停车场，1号停车场10个停车位没有停车，2号停车场20个停车位没有停车，ParkingRobot，一辆车
     * when 停车
     * then 停车成功并获得车票，停在了1号停车位
     * */
    @Test
    void should_park_car_at_no1_parking_lot_and_get_ticket_successfully_when_parking_car_given_no1_parking_lot_has_same_vacancy_rate_with_no2_parking_lot_and_parking_robot_and_one_car() {
        ParkingLot firstBiggestVacancyRateParkingLot = new ParkingLot(10);
        ParkingLot secondBiggestVacancyRateParkingLot = new ParkingLot(20);
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put("1", firstBiggestVacancyRateParkingLot);
        parkingLotMap.put("2", secondBiggestVacancyRateParkingLot);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLotMap);
        Car newCar = new Car();
        Ticket ticket = parkingRobot.parkingCar(newCar);
        Assertions.assertEquals(newCar, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 3.
     * given 两个停车场，1号停车场10个停车位停满，2号停车场20个停车位停满，ParkingRobot，一辆车
     * when 停车
     * then 停车失败并抛异常
     * */
    @Test
    void should_park_car_fail_and_throw_exception_when_parking_car_given_400_cars_parked_at_no1_parking_lot_and_600_cars_parked_at_no2_parking_lot_and_parking_robot_and_one_car() {
        ParkingLot firstBiggestVacancyRateParkingLot = new ParkingLot(400);
        ParkingLot secondBiggestVacancyRateParkingLot = new ParkingLot(600);
        for (int i = 0; i < 400; i++) {
            firstBiggestVacancyRateParkingLot.parkingCarAndGetTicket(new Car());
        }
        for (int i = 0; i < 600; i++) {
            secondBiggestVacancyRateParkingLot.parkingCarAndGetTicket(new Car());
        }
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put("1", firstBiggestVacancyRateParkingLot);
        parkingLotMap.put("2", secondBiggestVacancyRateParkingLot);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLotMap);
        Car newCar = new Car();
        Assertions.assertThrows(ParkingLotIsFullException.class, () -> parkingRobot.parkingCar(newCar));
    }

    /*
     * 4.
     * given 一个停车场，停车场10个停车位没有车，ParkingRobot，一辆车
     * when 停车
     * then 停车成功并获得车票
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_one_parking_lot_has_10_empty_parking_space() {
        ParkingLot parkingLot = new ParkingLot(10);
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put("1", parkingLot);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLotMap);
        Car car = new Car();
        Ticket ticket = parkingRobot.parkingCar(car);
        Assertions.assertEquals(car, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 5.
     * given 一个停车场，停车场10个停车位和一辆车，ParkingRobot，对应的车票
     * when 取车
     * then 抛不能取车异常
     * */
    @Test
    void should_throw_disabled_pike_up_car_when_take_the_car_given_parkingLot_has_one_car_and_parking_robot_and_one_matched_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        parkingLotMap.put("1", parkingLot);
        ParkingRobot parkingRobot = new ParkingRobot(parkingLotMap);
        Car car = new Car();
        Ticket ticket = parkingRobot.parkingCar(car);
        Assertions.assertThrows(DisabledPikeUpCarException.class, () -> parkingRobot.pickUpCar(ticket));
    }

}
