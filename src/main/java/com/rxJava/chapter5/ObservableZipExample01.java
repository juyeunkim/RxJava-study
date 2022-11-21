package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableZipExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 =
                Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .take(4);

        Observable<Long> observable2 =
                Observable.interval(400L, TimeUnit.MILLISECONDS)
                        .take(6);

        Observable.zip(observable1, observable2, (data1, data2) -> data1 + data2)
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(3000L);
    }
}
