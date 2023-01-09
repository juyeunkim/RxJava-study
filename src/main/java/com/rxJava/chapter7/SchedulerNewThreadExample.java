package com.rxJava.chapter7;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

public class SchedulerNewThreadExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        observable.subscribeOn(Schedulers.newThread()) // 구독할때마다 새로운 스레드를 생성
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.newThread())
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));


        TimeUtil.sleep(300L);
    }
}
