package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;

public class ObservableMaterialExample02 {
    public static void main(String[] args) {
        Observable.concatEager( // Observable 을 순차적으로 실행하지않고 동시에 실행 but 통지할때에는 먼저 입력받은 데이터부터 전달
                Observable.just(
                        getDBUser().subscribeOn(Schedulers.io()),
                        getAPIUser()
                                .subscribeOn(Schedulers.io())
                                .materialize()
                                .map(notification -> {
                                    if (notification.isOnError()) {
                                        // 관리자에게 에러 발생을 알림
                                        System.out.println("# API user 에러 발생!");
                                    }
                                    return notification;
                                })
                                .filter(notification -> !notification.isOnError())
                                .dematerialize(notification -> notification)
                )
        ).subscribe(
                data -> System.out.println("onNext : " + data),
                error -> System.out.println("onError : " + error),
                () -> System.out.println("onComplete")
        );

        TimeUtil.sleep(1000L);
    }

    private static Observable<String> getDBUser() {
        return Observable.fromIterable(Arrays.asList("DB user1", "DB user2", "DB user3", "DB user4", "DB user5"));
    }

    private static Observable<String> getAPIUser() {
        return Observable
                .just("API user1", "API user2", "Not User", "API user4", "API user5")
                .map(user -> {
                    if(user.equals("Not User"))
                        throw new RuntimeException();
                    return user;
                });
    }
}
