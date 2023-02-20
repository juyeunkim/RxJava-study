package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableRetryExample02 {
    private final static int RETRY_MAX = 5;
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
                                .retry((retryCount, ex) -> {
                                    System.out.println("# 재시도 횟수: " + retryCount);
                                    TimeUtil.sleep(1000L);
                                    return retryCount < RETRY_MAX ? true : false;
                                })
                                .onErrorReturn(throwable -> -1L)

                ).subscribe(
                data -> System.out.println("onNext : " + data),
                error -> System.out.println("onError :" + error),
                () -> System.out.println("complete")
        );


        TimeUtil.sleep(6000L);
    }
}
