package com.rxJava.chapter7;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

// Schedulers.trampoline() vs Schedulers.single()
//          스레드 생성 x                스레드 생성 o
public class SchedulerSingleExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        observable.subscribeOn(Schedulers.single()) // 새로운 스레드를 하나만 만들어서 순차적으로 처리
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.single()) // RxSingleScheduler-1
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(300L);
    }
}
