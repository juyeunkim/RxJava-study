package com.rxJava.chapter5.chapter0508;

import io.reactivex.Observable;

public class ObservableDefaultIfEmptyExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5)
                .filter(num -> num > 10)
                .defaultIfEmpty(10) // filter 의 결과가 empty 라면 10 만 방출, 아니면 filter 된 결과가 방출된다.
                .subscribe(data -> System.out.println("onNext : " + data));
    }
}
