package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableOnErrorResumeNextExample {
    public static void main(String[] args) {
        Observable.just(5L)
                .flatMap(num -> Observable
                        .interval(200L, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> num / i)
                        // 에러 발생시 새로운 Observable 를 리턴
                        .onErrorResumeNext(throwable -> {
                            System.out.println("# 운영자에게 이메일 발송: " + throwable.getMessage());
                            return Observable.interval(200L,TimeUnit.MILLISECONDS).take(5).skip(1).map(i -> num / i); // 1,2,3,4 를 방출
                        })
                ).subscribe(data -> System.out.println("doOnNext : " + data));

        TimeUtil.sleep(2000L);
    }
}
