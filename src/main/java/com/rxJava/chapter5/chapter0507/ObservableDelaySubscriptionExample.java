package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableDelaySubscriptionExample {
    public static void main(String[] args) {
        System.out.println("# 실행 시작 시간: " + TimeUtil.getCurrentTimeFormatted());

        Observable.just(1, 3, 4, 6)
                .doOnNext(data -> System.out.println(TimeUtil.getCurrentTimeFormatted() + " doOnNext : " + data))
                .delaySubscription(2000L, TimeUnit.MILLISECONDS) // subscribe 시간을 지연
                // 2초 지연 -> (doOnNext -> onNext) x N
                .subscribe(data -> System.out.println(TimeUtil.getCurrentTimeFormatted() +  " onNext : " + data));

        TimeUtil.sleep(2500L);

        /**
         * delay vs delaySubscription
         * - delay : 구독은 즉시 전달은 하지만(doOnNext) 소비자에 통지(subscribe) 를 지연
         * - delaySubscription : 구독 자체를 지연
         * */
    }
}
