package com.rxJava.chapter8;

import io.reactivex.Observable;
import utils.LogType;
import utils.Logger;

/**
 * onSubscribe 이벤트 발생 전에 호출
 * */
public class DoOnSubscribeExample {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .doOnSubscribe(disposable -> Logger.log(LogType.DO_ON_SUBSCRIBE, "# 생산자: 구독 처리 준비 완료"))
                .subscribe(
                        data -> Logger.log(LogType.ON_NEXT, data),
                        error -> Logger.log(LogType.ON_ERROR, error),
                        () -> Logger.log(LogType.ON_COMPLETE),
                        dispose -> Logger.log(LogType.ON_SUBSCRIBE, "# 소비자: 구독 처리 준비 완료 알림 받음")
                );
    }
}
