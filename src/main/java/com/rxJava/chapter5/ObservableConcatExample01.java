package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableConcatExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(500L, TimeUnit.MILLISECONDS)
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(300L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(num -> num + 1000);

        Observable.concat(observable2, observable1) // observable2 가 통지가 다 되고나서 observable1 데이터가 통지가된다.
                .subscribe(data -> System.out.println(data));


        TimeUtil.sleep(3500L);

    }
}
