package com.rxJava.chapter7;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.LogType;
import utils.Logger;

public class SchedulerTrampolineExample {
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5");

        observable.subscribeOn(Schedulers.trampoline()) // 현재 실행되고 있는 쓰레드의 대기큐에 등록하여, 순서대로 처리 (FIFO)
                .map(data -> "## " + data + " ##")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));

        observable.subscribeOn(Schedulers.trampoline()) // main
                .map(data -> "$$ " + data + " $$")
                .subscribe(data -> Logger.log(LogType.ON_NEXT, data));
    }
}
