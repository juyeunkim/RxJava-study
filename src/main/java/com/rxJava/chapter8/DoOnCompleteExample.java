package com.rxJava.chapter8;

import io.reactivex.Observable;
import utils.LogType;
import utils.Logger;

/**
 * 생산자가 완료를 통지하는 시점에, 지정된 작업을 처리할 수 있다.
 * onComplete 이벤트가 발생하기 직전에 실행
 *
 * doOnTerminate : 완료 또는 에러가 통지될 때 호출되는 함수 (doOnComplete + doOnError)
 * doAfterTerminate : 완료 또는 에러가 통지된 후 호출되는 함수 (after doOnComplete + doOnError)
 * */
public class DoOnCompleteExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
               .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료"))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
