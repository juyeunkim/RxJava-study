package com.rxJava.chapter5;


import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableCombineLatestExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(500L, TimeUnit.MILLISECONDS)
                        .doOnNext(data -> System.out.println("# observable 1 : " + data))
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(700L, TimeUnit.MILLISECONDS)
                        .doOnNext(data -> System.out.println("# observable 2 : " + data))
                        .take(4);

        Observable.combineLatest(
                observable1,
                observable2,
                (data1, data2) -> "data1: " + data1 + "\tdata2: " + data2)
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(3000L);
    }
}
