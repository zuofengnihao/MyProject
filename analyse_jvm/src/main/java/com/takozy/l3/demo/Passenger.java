package com.takozy.l3.demo;

public abstract class Passenger {
    abstract void passThroughImmigration();

    public static void main(String[] args) {
        Passenger c = new ChinesePassenger();
        Passenger f = new ForeignerPassenger();
        long current = System.currentTimeMillis();
        for (int i = 1; i <= 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            Passenger p = (i < 100_000_000) ? c : f;
            p.passThroughImmigration();
        }
    }
}


class ChinesePassenger extends Passenger {

    @Override
    void passThroughImmigration() {

    }
}

class ForeignerPassenger extends Passenger {

    @Override
    void passThroughImmigration() {

    }
}