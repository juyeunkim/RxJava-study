package com.rxJava.chapter5;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableIntervalExample {
    public static void main(String[] args){
//        System.out.println("# start : " +TimeUtil.getCurrentTimeFormatted());

        // 독립된 스레드에서 진행
        Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .map(num -> num + " count")
                .subscribe(data -> System.out.println(data)//Logger.log(LogType.ON_NEXT, data)
                );

//        TimeUtil.sleep(3000);
    }
}
