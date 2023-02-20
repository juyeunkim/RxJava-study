package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;

public class ObservableDelayExample02 {
    public static void main(String[] args) {
        Observable.just(1,3,5,7)
                // 새로운 데이터를 받을때마다 지연
                .delay(item -> {
                    TimeUtil.sleep(1000L);
                    return Observable.just(item); // 새로운 Observable의 통지 시점에, 원본 데이터를 통지한다.
                }).subscribe(data -> System.out.println(TimeUtil.getCurrentTimeFormatted() + " onNext : " + data));
    }
}
