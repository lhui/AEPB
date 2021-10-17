package com.example.AEPB.aircoin;


public class AirCoin {
    private final Long amount;
    private static final long AMOUNT_MAX = 1000000000L;
    private static final long AMOUNT_MIN = 0L;

    public AirCoin(Long amount) {
        this.amount = amount;
    }

    public boolean compareAmountIsEqual(AirCoin otherAirCoin) {
        if (this.amount == null || otherAirCoin.amount == null) {
            throw new AirCoinAmountIsNullException("AirCoin amount can not be null");
        }

        if (this.amount < AMOUNT_MIN || otherAirCoin.amount < AMOUNT_MIN || this.amount > AMOUNT_MAX || otherAirCoin.amount > AMOUNT_MAX) {
            throw new AirCoinAmountBeyondLimitException("AirCoin amount beyond limit");
        }
        return this.amount.equals(otherAirCoin.amount);
    }
}

