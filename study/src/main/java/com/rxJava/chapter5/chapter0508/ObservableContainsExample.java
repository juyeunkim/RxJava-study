package com.rxJava.chapter5.chapter0508;

import common.CarMaker;
import common.SampleData;
import io.reactivex.Observable;

public class ObservableContainsExample {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakersDuplicated)
                .doOnNext(data -> System.out.println("doOnNext : " + data))
                .contains(CarMaker.SAMSUNG)
                // 일치하는 조건이 있으면 true 을 리턴하고, 중단
                //             없다면 false 를 리턴
                .subscribe(data -> System.out.println("onNext : " + data));
    }
}
