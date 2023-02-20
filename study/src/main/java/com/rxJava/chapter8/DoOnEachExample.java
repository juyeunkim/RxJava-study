package com.rxJava.chapter8;

import io.reactivex.Observable;
import utils.LogType;
import utils.Logger;

/**
 * doOnNext, doOnComplete, doOnError 를 한번에 처리할 수 있다
 * Notification 객체를 함수형 인터페이스의 파라미터로 전달 받아서 처리한다.
 * */
public class DoOnEachExample {
    public static void main(String[] args) {
        Observable.range(1, 5)
                .doOnEach(notification -> {
                    // subscribe 에서 실행된 함수를 받아와서 처리
                    if(notification.isOnNext())
                        Logger.log(LogType.DO_ON_NEXT, "# 생산자: 데이터 통지 - " + notification.getValue());
                    else if(notification.isOnError())
                        Logger.log(LogType.DO_ON_ERROR, "# 생산자: 에러 발생 - " + notification.getError());
                    else // notification.isOnComplete()
                        Logger.log(LogType.DO_ON_COMPLETE, "# 생산자: 데이터 통지 완료");
                })
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE)
                );
    }
}
