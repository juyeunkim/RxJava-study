package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableSwitchMapExample01 {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("# start : " + TimeUtil.getCurrentTimeFormatted());
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .take(4)
                .skip(2)
                .doOnNext(data -> System.out.println("doOnNext : "+ data))
                .switchMap(
                        // doOnNext 에서 2 발행 -> switchMap 에서 interval 300L 에 타임이 맞지않아 발행 X
                        // doOnNext 에서 3 발행 -> 타임이 지난후 데이터를 발행하는데, 2에서 처리중이었던 작업을 중단 (2단의 구구단 작업 X)
                        //                       subscribe 에서는 3단만 출력된다.
                        num -> Observable.interval(300L, TimeUnit.MILLISECONDS)
                                .take(10)
                                .skip(1)
                                .map(row -> num + " * " + row + " = " + num * row)
                )
                .subscribe(data -> System.out.println(data))
        ;

        Thread.sleep(5000);
    }
}
