package com.rxJava.chapter5.quiz;

import common.CarMaker;
import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class Quiz {
    // filter 를 이용하여 SampleDataList 중에서 CarMaker 가 SSANGYOUNG 인 차들의 carName 을 출력
    static void quiz1(){
        Observable.fromIterable(SampleData.carList)
                .filter(car -> car.getCarMaker().equals(CarMaker.SSANGYOUNG))
                .subscribe(data -> System.out.println(data.getCarName()));
    }

    // interval, takeWhile 를 이용하여 발행된 숫자가 10이 아닌동안 데이터를 1초에 한번씩 계속 출력
    static void quiz2(){
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .takeWhile(t -> t < 10L)
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(100000L);
    }

    // interval, skipUntil, timer 를 이용하여 1초에 한번씩 발행된 데이터 중에서 3초 후에 발행된 데이터만 10까지 출력
    static void quiz3(){
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .skipUntil(Observable.timer(3000L, TimeUnit.MILLISECONDS))
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(11000L);
    }

    // range skipLast 를 이용하여 1부터 15까지의 숫자중에서 마지막에 발행된 숫자 3개를 제외한 나머지 숫자를 출력
    static void quiz4(){
        Observable.range(1, 15)
                .skipLast(3)
                .subscribe(data -> System.out.println(data))
                ;

    }

    public static void main(String[] args) {
//        quiz1();
//        quiz2();
//        quiz3();
        quiz4();

    }


}
