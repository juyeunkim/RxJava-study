package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorReturnExample {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> num / i)
                        .onErrorReturn(exception -> {
                            if(exception instanceof ArithmeticException)
                                System.out.println("계산 처리 에러 발생: " + exception.getMessage());

                            return -1L;
                        })
                )
                .subscribe(
                        data -> {
                            if(data < 0)
                                System.out.println("# 예외를 알리는 데이터: " + data);
                            else
                                System.out.println("onNext : " + data);
                        },
                        error -> System.out.println("onError : " + error), // onErrorReturn 을 통해서 결과를 리턴하면 onError 는 거치지 않게되고 doOnNext 를 거치게된다.
                        () -> System.out.println("complete")
                );

        TimeUtil.sleep(1000L);
    }
}
