package com.company.atomic_counter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            new CounterThread(counter).start();
        }

        Thread.sleep(1000);

        System.out.println(counter.getCounter());
    }
}

class Counter {
    private AtomicInteger counter = new AtomicInteger(0);
    private AtomicReference<Double> doubleAtomicReference = new AtomicReference<>();
    //increment to 1, version 1
    //...
    //increment to 9999, version 9999
    //increment to 9998, version 9998
    //                   version 9997

    public int getCounter() {
        return counter.get();
    }

    public void increment() {
//        AtomicInteger atomicInteger = counter;
        counter.getAndIncrement();
//        counter = atomicInteger;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2_000; i++) {
            counter.increment();
        }
    }
}