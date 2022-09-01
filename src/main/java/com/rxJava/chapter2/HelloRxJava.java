package com.rxJava.chapter2;

import io.reactivex.Observable;

public class HelloRxJava {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("Hello", "RxJava");

        observable.subscribe(data -> System.out.println(data));
    }
}
