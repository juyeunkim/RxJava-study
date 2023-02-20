package com.rxJava.chapter5;

import io.reactivex.Observable;

import java.time.LocalTime;

public class ObservableDeferExample {
    public static void main(String[] args) throws InterruptedException {
        Observable<LocalTime> observable = Observable.defer(() -> {
            LocalTime currentTime = LocalTime.now();
            return Observable.just(currentTime);
        });

        Observable<LocalTime> observableJust = Observable.just(LocalTime.now());

        observable.subscribe(time -> System.out.println(" # defer() 구독1의 구독 시간: " + time));
        observableJust.subscribe(time -> System.out.println(" # just() 구독1의 구독 시간: " + time));

        Thread.sleep(3000);

        observable.subscribe(time -> System.out.println(" # defer() 구독2의 구독 시간: " + time));
        observableJust.subscribe(time -> System.out.println(" # just() 구독자2의 구독 시간: " + time));
        // observable 는 구독시점 현재시간을 호출
        // just 는 선언할때 만들어진 시간을 출력
    }
}
