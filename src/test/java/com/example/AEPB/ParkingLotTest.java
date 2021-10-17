package com.example.AEPB;

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
public class ParkingLotTest {
}
