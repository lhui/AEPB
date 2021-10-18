package com.example.AEPB;

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
        Ticket ticket = parkingLotGroup.parkingCarAndGetTicketByParkingBoy(car);
        Assertions.assertEquals(1, parkingLotGroup.getTicketCountByParkingNumber(1));
        Assertions.assertEquals(49, parkingLotGroup.getParkingLotSpaceByParkingNumber(1));
        Assertions.assertEquals(50, parkingLotGroup.getParkingLotSpaceByParkingNumber(2));
        Assertions.assertEquals(UUID.randomUUID().toString().length(), ticket.getTicketNumber().length());
    }


}
