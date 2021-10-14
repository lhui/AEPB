package com.example.AEPB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

//        given 一个面值为0的AirCoin，另一个面值为0的AirCoin货币
//        When 比较
//        Then 相等
//
//        given 一个面值为-1的AirCoin，另一个面值为-1的AirCoin货币
//        When 比较
//        Then 抛出异常
//
//        given 一个面值为10亿的AirCoin，另一个面值为10亿的AirCoin货币
//        When 比较
//        Then 相等
//
//        given 一个面值为10亿+1的AirCoin，另一个面值为10亿+1的AirCoin货币
//        When 进行比较
//        Then 抛出异常
//
//        given 一个面值为1的AirCoin，另一个面值为2的AirCoin货币
//        When 比较
//        Then 不相等
//
//        given 一个面值为1的AirCoin，另一个面值为null的AirCoin货币
//        When 比较
//        Then 抛出异常

class AirCoinTest {

    @Test
    void should_get_equal_result_when_compare_amount_is_equal_given_a_airCoin_amount_is_0_and_another_airCoin_amount_is_0() {
        AirCoin airCoin = new AirCoin(0L);
        AirCoin otherAirCoin = new AirCoin(0L);
        Assertions.assertTrue(airCoin.compareAmountIsEqual(otherAirCoin));
    }

    @Test
    void should_throw_exception_when_compare_amount_is_equal_given_a_airCoin_amount_is_negative_1_and_another_airCoin_amount_is_negative_1() {
        AirCoin airCoin = new AirCoin(-1L);
        AirCoin otherAirCoin = new AirCoin(-1L);
        assertThrows(AirCoinAmountBeyondLimitException.class, () -> airCoin.compareAmountIsEqual(otherAirCoin));
    }

    @Test
    void should_get_equal_result_when_compare_amount_is_equal_given_a_airCoin_amount_is_one_billion_and_another_airCoin_amount_is_one_billion() {
        AirCoin airCoin = new AirCoin(1000000000L);
        AirCoin otherAirCoin = new AirCoin(1000000000L);
        Assertions.assertTrue(airCoin.compareAmountIsEqual(otherAirCoin));
    }

    @Test
    void should_throw_exception_when_compare_amount_is_equal_given_a_airCoin_amount_is_one_billion_plus_one_and_another_airCoin_amount_is_one_billion_plus_one() {
        AirCoin airCoin = new AirCoin(1000000001L);
        AirCoin otherAirCoin = new AirCoin(1000000001L);
        assertThrows(AirCoinAmountBeyondLimitException.class, () -> airCoin.compareAmountIsEqual(otherAirCoin));
    }

    @Test
    void should_get_not_equal_result_when_compare_amount_is_equal_given_a_airCoin_amount_is_1_and_another_airCoin_amount_is_2() {
        AirCoin airCoin = new AirCoin(1L);
        AirCoin otherAirCoin = new AirCoin(2L);
        Assertions.assertFalse(airCoin.compareAmountIsEqual(otherAirCoin));
    }

    @Test
    void should_throw_exception_when_compare_amount_is_equal_given_a_airCoin_amount_is_1_and_another_airCoin_amount_is_null() {
        AirCoin airCoin = new AirCoin(1L);
        AirCoin otherAirCoin = new AirCoin(null);
        assertThrows(AirCoinAmountIsNullException.class, () -> airCoin.compareAmountIsEqual(otherAirCoin));
    }

}
