package com.company.synchronized_counter;

public class Main {
    /**
     *
     * Synchronized - по одному (одна касса - два человека -> кто-то ждет)
     * Asynchronized
     * Asynch - одновременно (две кассы - два человека)
     *
     */
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        //99 тредов
        //synchronized {
        //1 тред
        for (int i = 0; i < 10; i++) {
            new CounterThread(counter).start();
        }
        //тред закончил. Заходи, кто там в очереди
        //}

        Thread.sleep(3000);

        System.out.println(counter.getCounter());
    }
}


class Counter {
    private int counter = 0;
    private Object key = new Object();


    public int getCounter() {
        return counter;
    }

    public void increment() {
        //сколько угодно потоков
        synchronized (key) { //monitor - блокировка по объекту,
                            //когда объект может сказать: блокирует ли его
                            //какой-то другой тред или нет
            //один поток в одно и то же время
            counter++;
        }
    }
}

class CounterThread extends Thread {
    private Counter counter;
    private Object key = new Object();

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        //JVM - автомат
        //ClassLoader - магазин, который "подгружает" патроны
        //Classes - патроны
        //
        //Before JVM run
        //ClassLoader - перечень всех классов, которые нужно загрузить

        //After JVM run
        //Memory allocation (выделение памяти)
        //static variables (Person.city)
        //instance objects (new Person)
//        synchronized (Object.class) {
            for (int i = 0; i < 2_000; i++) {
                counter.increment();
            }
//        }
    }

}