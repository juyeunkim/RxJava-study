package com.rxJava.chapter7;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

import java.io.File;

public class SchedulerIOExample03 {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/com/rxJava/").listFiles();

        Observable.fromArray(files)
                .doOnNext(file -> Logger.log(LogType.DO_ON_NEXT, "# 데이터 통지"))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()) // RxComputationThreadPool-1
                .filter(data -> data.isDirectory())
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter() 거침"))
                .observeOn(Schedulers.computation()) // RxComputationThreadPool-2
                .map(dir -> dir.getName())
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map() 거침"))
                .observeOn(Schedulers.computation()) // RxComputationThreadPool-3
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}
