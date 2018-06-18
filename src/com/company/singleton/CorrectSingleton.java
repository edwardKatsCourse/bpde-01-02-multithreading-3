package com.company.singleton;

public class CorrectSingleton {

    private static CorrectSingleton instance;

    public static synchronized CorrectSingleton getInstance() {
        if (instance == null) {
            instance = new CorrectSingleton();
        }
        return instance;
    }

    private CorrectSingleton() {
        System.out.println("Подключаюсь к чему-то ОЧЕЕНЬ медленному...");
    }


}
