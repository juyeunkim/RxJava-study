package com.rxJava.chapter8;

import io.reactivex.Observable;
import utils.LogType;
import utils.Logger;

/**
 * onNext 이벤트가 발생하기 직전에 실행
 * 생산자가 데이터를 통지하는 시점에, 지정된 작업을 처리한다.
 *
 * <-> doAfterNext : 생산자가 통지한 데이터가 소비자에 전달된 직후 호출되는 함수
 * */
public class DoOnNextExample {
    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9, 10, 11, 12, 13)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# 원본 통지 데이터: " + data))
                .filter(data -> data < 10)
                // 10 이후에는 밑에 실행 x
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# filter 적용 후: " + data))
                .map(data -> "#### " + data + " ####")
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, "# map 적용 후: " + data))
                .subscribe(data -> Logger.log(LogType.ON_NEXT, "# 최종 데이터: " + data));
    }
}
