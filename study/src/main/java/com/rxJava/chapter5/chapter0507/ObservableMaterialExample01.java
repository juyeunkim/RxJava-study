package com.rxJava.chapter5.chapter0507;

import io.reactivex.Observable;

public class ObservableMaterialExample01 {
    public static void main(String[] args) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .materialize() // 통지된 데이터와 타입을 리턴 => 메타데이터를 리턴한다 Notification<T>
                .subscribe(notification -> {
                    String notificationType =
                            notification.isOnNext() ? "onNext()" : (notification.isOnError() ? "onError()" : "onComplete()");
                    System.out.println("notification 타입: " + notificationType);
                    System.out.println("onNext : " + notification.getValue()); // onComplete 이면 value null
                });
    }
}
