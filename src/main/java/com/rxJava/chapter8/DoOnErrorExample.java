package com.rxJava.chapter8;

import io.reactivex.Observable;
import utils.LogType;
import utils.Logger;

/**
 * 생산자가 에러를 통지하는 시점에, 지정된 작업을 처리할 수 있다
 * onError 이벤트가 발생하기 직전에 실행
 * 통지된 에러 객체가 함수형 인터페이스의 파라미터로 전달되므로 에러 상태를 확인할 수 있다.
 * */
public class DoOnErrorExample {
    public static void main(String[] args) {
        Observable.just(3, 6, 9, 12, 15, 20)
            .zipWith(Observable.just(1, 2, 3, 4, 0, 5), (a, b) -> a / b)
            .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, "# 생산자: 에러 발생 - " + error.getMessage()))
            .subscribe(
                    data -> Logger.log(LogType.ON_NEXT, data),
                    error -> Logger.log(LogType.ON_ERROR, error)
            );
}
}
