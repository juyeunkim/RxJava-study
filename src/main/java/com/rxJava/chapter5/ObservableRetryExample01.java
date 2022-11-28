package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample01 {
    public static void main(String[] args) {
        Observable.just(5)
                .flatMap(
                        num -> Observable
                                .interval(200L, TimeUnit.MILLISECONDS)
                                .map(i -> {
                                    long result;
                                    try{
                                        result = num / i;
                                    }catch(ArithmeticException ex){
                                        System.out.println("error: " + ex.getMessage());
                                        throw ex;
                                    }
                                    return result;
                                })
                                .retry(5) // onError 이벤트가 발생하면 subscribe() 를 재호출하여 다시 구독한다.
                                .onErrorReturn(throwable -> -1L)
                ).subscribe(
                data -> System.out.println("onNext : " + data),
                error -> System.out.println("onError : " + error),
                () -> System.out.println("complete")
        );

        TimeUtil.sleep(5000L);
    }
}
