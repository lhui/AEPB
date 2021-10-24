package com.example.AEPB;

import com.example.AEPB.parkingBoy.ParkingBoy;
import com.example.AEPB.parkinglot.Car;
import com.example.AEPB.parkinglot.ParkingLot;
import com.example.AEPB.parkinglot.exception.CarNotFoundException;
import com.example.AEPB.parkinglot.exception.NullTicketCanNotGetCarException;
import com.example.AEPB.parkinglot.exception.ParkingLotIsFullException;
import com.example.AEPB.parkinglot.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class ParkingBoyTest {
    /*
     * 1、
     * given 10个停车场都未停车，ParkingBoy，停放一辆车
     * when 停车
     * then 停车成功并获得车票，确定停到1号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_ten_empty_parkingLots_and_parking_boy_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        Car car = new Car();
        Ticket ticket = parkingBoy.parkingCar(car);
        Assertions.assertEquals(car, parkingBoy.pickUpCar(ticket));
    }

    private Map<String, ParkingLot> creatParkingLots() {
        Map<String, ParkingLot> parkingLotMap = new HashMap<>();
        IntStream.rangeClosed(1, 10).forEach(parkingLotNumber -> {
            ParkingLot parkingLot = new ParkingLot();
            parkingLotMap.put(String.valueOf(parkingLotNumber), parkingLot);
        });
        return parkingLotMap;
    }

    /*
     * 2、
     * given 10个停车场都未停车，自己将一辆车停1号停车场，
     * when 停车
     * then 停车成功并获得车票，并确定停到1号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_ten_empty_parkingLots_and_self_parking_No1_parkingLot_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(car);
        Assertions.assertEquals(car, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 3、
     * given 10个停车场都停满，ParkingBoy，停放一辆车
     * when 停车
     * then 停车失败抛出异常
     * */
    @Test
    void should_park_car_and_get_ticket_failed_when_parking_car_given_ten_full_parkingLots_and_parking_boy_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        for (int parkingLotNo = 1; parkingLotNo <= 10; parkingLotNo++) {
            for (int carNo = 0; carNo < 50; carNo++) {
                Car car = new Car();
                parkingLotMap.get(String.valueOf(parkingLotNo)).parkingCarAndGetTicket(car);
            }
        }
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        Car extraCar = new Car();
        Assertions.assertThrows(ParkingLotIsFullException.class, () -> parkingBoy.parkingCar(extraCar));
    }

    /*
     * 4、
     * given 10个停车场都停满，自己停一辆车到1号停车场，
     * when 停车
     * then 停车失败抛出异常
     * */
    @Test
    void should_park_car_and_get_ticket_failed_when_parking_car_given_ten_full_parkingLots_and_self_parking_No1_parkingLot_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        for (int parkingLotNo = 1; parkingLotNo <= 10; parkingLotNo++) {
            for (int carNo = 0; carNo < 50; carNo++) {
                Car car = new Car();
                parkingLotMap.get(String.valueOf(parkingLotNo)).parkingCarAndGetTicket(car);
            }
        }
        Car extraCar = new Car();
        Assertions.assertThrows(ParkingLotIsFullException.class, () -> parkingLotMap.get("1").parkingCarAndGetTicket(extraCar));
    }

    /*
     * 5、
     * given 10个停车场都未停车，自己停一辆车到2号停车场，
     * when 停车
     * then 停车成功并获得车票，并确定停到2号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_ten_empty_parkingLots_and_self_parking_No2_parkingLot_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("2").parkingCarAndGetTicket(car);
        Assertions.assertEquals(car, parkingLotMap.get("2").getCar(ticket));
    }

    /*
     * 6、
     * given 1号停车场有一个空位，2号停车场停了一辆车，ParingBoy，停放一辆车
     * when 停车
     * then 停车成功并获得车票，并确定停到1号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_No1_parkingLot_has_49_cars_and_No2_parkingLot_has_one_car_and_parking_boy_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        for (int carNo = 0; carNo < 49; carNo++) {
            Car car = new Car();
            parkingBoy.parkingCar(car);
        }
        Car no2ParkingLotCar = new Car();
        parkingLotMap.get("2").parkingCarAndGetTicket(no2ParkingLotCar);
        Car newCar = new Car();
        Ticket ticket = parkingBoy.parkingCar(newCar);
        Assertions.assertEquals(newCar, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 7、
     * given 1号停车场有一个空位，2号停车场停了一辆车，自己停一辆车2号停车场
     * when 停车
     * then 停车成功并获得车票，确定停到2号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_No1_parkingLot_has_49_cars_and_No2_parkingLot_has_one_car_and_self_parking_No2_parkingLot_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        for (int carNo = 0; carNo < 49; carNo++) {
            Car car = new Car();
            parkingBoy.parkingCar(car);
        }
        Car no2ParkingLotCar = new Car();
        parkingLotMap.get("2").parkingCarAndGetTicket(no2ParkingLotCar);
        Car newCar = new Car();
        Ticket ticket = parkingLotMap.get("2").parkingCarAndGetTicket(newCar);
        Assertions.assertEquals(newCar, parkingLotMap.get("2").getCar(ticket));
    }

    /*
     * 8、
     * given 1号停车场有一个空位，2号停车场停了一辆车，自己停放一辆车到1号停车场
     * when 停车
     * then 停车成功并获得车票，并确定停到1号停车场
     * */
    @Test
    void should_park_car_and_get_ticket_successfully_when_parking_car_given_No1_parkingLot_has_49_cars_and_No2_parkingLot_has_one_car_and_self_parking_No1_parkingLot_and_one_parking_car() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        for (int carNo = 0; carNo < 49; carNo++) {
            Car car = new Car();
            parkingBoy.parkingCar(car);
        }
        Car no2ParkingLotCar = new Car();
        parkingLotMap.get("2").parkingCarAndGetTicket(no2ParkingLotCar);
        Car newCar = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(newCar);
        Assertions.assertEquals(newCar, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 9、
     * given 1号停车场停有一辆车，自己使用对应票取1号停车场到对应车
     * when 取车
     * then 取车成功
     * */
    @Test
    void should_get_car_successfully_when_take_the_car_given_No1_parkingLot_has_one_cars_and_self_take_No1_parkingLot_and_one_matched_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(car);

        Assertions.assertEquals(car, parkingLotMap.get("1").getCar(ticket));
    }

    /*
     * 10、
     * given 1号停车场停有一辆车，自己1号停车场的对应票取2号停车场
     * when 取车
     * then 取车失败并抛异常
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_self_take_No2_parkingLot_and_one_matched_ticket_for_No1_parkingLot() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(car);
        Assertions.assertThrows(CarNotFoundException.class, () -> parkingLotMap.get("2").getCar(ticket));
    }

    /*
     * 11、
     * given 1号停车场停有一辆车，给ParkingBoy对应票
     * when 取车
     * then 取车成功
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_parking_boy_and_one_matched_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        Ticket ticket = parkingLotMap.get("1").parkingCarAndGetTicket(car);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        Assertions.assertEquals(car, parkingBoy.pickUpCar(ticket));
    }

    /*
     * 12、
     * given 1号停车场停有一辆车，自己无效票取1号停车场
     * when 取车
     * then 取车失败并抛异常
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_self_take_No1_parkingLot_and_one_invalid_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        parkingLotMap.get("1").parkingCarAndGetTicket(car);
        Assertions.assertThrows(NullTicketCanNotGetCarException.class, () -> parkingLotMap.get("1").getCar(null));
    }

    /*
     * 13、
     * given 1号停车场停有一辆车，自己无效票取2号停车场到车
     * when 取车
     * then 取车失败并抛异常
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_self_take_No2_parkingLot_and_one_invalid_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        parkingLotMap.get("1").parkingCarAndGetTicket(car);
        Assertions.assertThrows(NullTicketCanNotGetCarException.class, () -> parkingLotMap.get("2").getCar(null));
    }

    /*
     * 14、
     * given 1号停车场停有一辆车，给ParkingBoy无效票
     * when 取车
     * then 取车失败并抛异常
     * */
    @Test
    void should_get_car_failed_and_throw_exception_when_take_the_car_given_No1_parkingLot_has_one_cars_and_parking_boy_and_one_invalid_ticket() {
        Map<String, ParkingLot> parkingLotMap = creatParkingLots();
        Car car = new Car();
        parkingLotMap.get("1").parkingCarAndGetTicket(car);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMap);
        Assertions.assertThrows(NullTicketCanNotGetCarException.class, () -> parkingBoy.pickUpCar(null));
    }

}
