package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableDelayExample01 {
    public static void main(String[] args) {
        System.out.println("# 실행 시작 시간: " + TimeUtil.getCurrentTimeFormatted());

        Observable.just(1, 3, 4, 6)
                .doOnNext(data -> System.out.println(TimeUtil.getCurrentTimeFormatted() + " doOnNext : " + data)) //
                .delay(2000L, TimeUnit.MILLISECONDS) // 2초 지연, (doOnNext -> 2초 지연 -> onNext) x N
                .subscribe(data -> System.out.println(TimeUtil.getCurrentTimeFormatted() +  " onNext : " + data)); // doOnNext 를 다 출력한 다음 2초뒤 호출

        TimeUtil.sleep(2500L);
    }
}
