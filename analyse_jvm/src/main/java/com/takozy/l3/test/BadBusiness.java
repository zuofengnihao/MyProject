package com.takozy.l3.test;

import java.util.Random;

public class BadBusiness extends Business {
    @Override
    public double price(double p, Customer c) {
        if (c.isVIP()) return p * discrimination();
        else return super.price(p, c);
    }

    public static double discrimination() {
        return new Random().nextDouble() + 0.8;
    }
}
