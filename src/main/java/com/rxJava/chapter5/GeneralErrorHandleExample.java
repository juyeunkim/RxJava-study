package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class GeneralErrorHandleExample {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .doOnNext(data -> System.out.println("doOnNext : " + data))
                        .take(5)
                        .map(i -> num / i))
                .subscribe(
                        data -> System.out.println("onNext : " + data),
                        error -> System.out.println("onError : " + error), // 에러 발생시 onError 로 결과 리턴
                        () -> System.out.println("complete")
                );

        TimeUtil.sleep(1000L);
    }
}
