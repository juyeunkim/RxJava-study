package com.rxJava.chapter7;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

import java.io.File;

public class SchedulerIOExample02 {
    public static void main(String[] args) {
        File[] files = new File("src/main/java/com/rxJava/").listFiles();

        Observable.fromArray(files)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .subscribeOn(Schedulers.io()) // 통지하는쪽에 RxCachedThreadScheduler
                .observeOn(Schedulers.computation()) // 처리하는쪽에 RxComputationThreadPool
                .filter(data -> data.isDirectory())
                .map(dir -> dir.getName())
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        TimeUtil.sleep(1000L);
    }
}
