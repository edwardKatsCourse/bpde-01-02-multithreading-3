package com.company.synchronized_method;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new CounterThread(counter).start();
        }

        Thread.sleep(2000);

        System.out.println(counter.getCounter());
    }
}

class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public synchronized void increment() { //synchronized(this)
        counter++;
    }

    public synchronized void method() { //synchronized(this)
//        synchronized (this) {
            System.out.println("работаю....");
//        }
    }

    public static synchronized void staticMethod() { //synchronized(Counter.class)
//        synchronized (Counter.class) {
            System.out.println("я статический....");
//        }
    }
}

class CounterThread extends Thread {

    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {

//        Counter counterCache = counter;
        for (int i = 0; i < 2_000; i++) {
//            counterCache.increment();
            counter.increment();
        }

//        counter = counterCache;
    }
}