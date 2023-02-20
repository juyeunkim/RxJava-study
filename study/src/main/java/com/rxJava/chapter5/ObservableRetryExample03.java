package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

public class ObservableRetryExample03 {
    private final static int RETRY_MAX = 5;
    public static void main(String[] args) {
        Observable.just(10, 12, 15, 16)
                .zipWith(Observable.just(1, 2, 0, 4), (a, b) -> {
                    int result;
                    try{
                        result = a / b;
                    }catch(ArithmeticException ex){
                        System.out.println("error: " + ex.getMessage());
                        throw ex;
                    }
                    return result;
                })
                .retry(3)
                .onErrorReturn(throwable -> -1) // retry 의 시도 후, -1 결과값 리턴
                .subscribe(
                        data -> System.out.println("onNext : " + data),
                        error -> System.out.println("onError : " + error),
                        () -> System.out.println("complete")
                );

        TimeUtil.sleep(5000L);
    }
}
