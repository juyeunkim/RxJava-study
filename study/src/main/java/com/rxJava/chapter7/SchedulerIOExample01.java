package com.rxJava.chapter7;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

import java.io.File;

public class SchedulerIOExample01 {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/com/rxJava/").listFiles();

        Observable.fromArray(files)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data.getName()))
                .filter(data -> data.isDirectory())
                .map(dir -> dir.getName())
                .subscribeOn(Schedulers.io()) // 데이터 통지와 처리를 RxCachedThreadScheduler 에서 처리
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);

    }
}
