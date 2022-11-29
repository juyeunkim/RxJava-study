package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableTimeOutExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
                .map(num -> {
                    long time = 1000L;
                    if(num == 4){
                        time = 1500L;
                    }
                    TimeUtil.sleep(time);
                    return num;
                })
                .timeout(1200L, TimeUnit.MILLISECONDS) // 1.2초 안에 통지된 데이터만 구독
                .subscribe(
                        data -> System.out.println(TimeUtil.getCurrentTimeFormatted() +  " onNext : " + data),
                        error -> System.out.println(TimeUtil.getCurrentTimeFormatted() +  " onError : " + error) // TimeoutException 발생
                );

        TimeUtil.sleep(4000L);
    }
}
