package com.company.non_synchronized_counter;

public class Main {
    /**
     * thread local cache
     * synchronized
     * atomic types - атомарные типы
     * volatile - (google translate) летучий, изменчивый
     */

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        for (int i = 0; i < 10; i++) {
            new CounterThread(counter).start();
        }

        Thread.sleep(1000);

        System.out.println(counter.getCounter()); //expected = 20_000, actual = 18701
    }
}

class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increment() {
        counter++;
    }
}

class CounterThread extends Thread {
    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }



    /*int intCounter = 0;*/

    @Override
    public void run() {
        /*
        THREAD LOCAL CACHE
        Counter cachedValue = counter;
        cachedValue.increment();
        counter = cachedValue;
        */
        /*
        int cachedCounter = intCounter; //intCounter = 1115
        cachedCounter++;                //intCounter = 1 (after two threads)
        intCounter = cachedCounter;
        */



        for (int i = 0; i < 2_000; i++) {
            counter.increment();
        }
    }
}