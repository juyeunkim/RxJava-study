package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableConcatMapExample01 {
    public static void main(String[] args) {
        usingConcatMap();

        usingFlatMap();
    }

    // 실행 속도가 느림, but 순서가 보장
    public static void usingConcatMap() {
        System.out.println("======= ConcatMap =======");
        TimeUtil.start();
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .concatMap(
                        num -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                                .take(10)
                                .skip(1)
                                .map(row -> num + " * " + row + " = " + num * row)
                ).subscribe(
                data -> System.out.println(data),
                error -> {},
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
        );

        TimeUtil.sleep(5000L);
    }

    // 속도가 빠름, but 실행 순서 보장 X
    public static void usingFlatMap() {
        System.out.println("======= FlatMap =======");
        TimeUtil.start();
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .flatMap(
                        num -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                                .take(10)
                                .skip(1)
                                .map(row -> num + " * " + row + " = " + num * row)
                ).subscribe(
                data -> System.out.println(data),
                error -> {},
                () -> {
                    TimeUtil.end();
                    TimeUtil.takeTime();
                }
        );

        TimeUtil.sleep(5000L);
    }
}
