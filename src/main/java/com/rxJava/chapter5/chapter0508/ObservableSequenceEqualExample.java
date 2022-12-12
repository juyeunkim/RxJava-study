package com.rxJava.chapter5.chapter0508;

import common.CarMaker;
import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ObservableSequenceEqualExample {
    // 동일한 순서, 동일한 데이터
    // 통지된 시점과 무관하게 데이터의 정합성만 판단.
    public static void main(String[] args) {
        Observable<CarMaker> observable1 =
                Observable
                        .fromArray(SampleData.carMakers)
                        .subscribeOn(Schedulers.computation()) // main thread 가 아닌 다른 thread 에서 실행
                        .delay(carMaker -> {
                            TimeUtil.sleep(500L);
                            return Observable.just(carMaker);
                        }).doOnNext(data -> System.out.println(Thread.currentThread().getName() + ", doOnNext # observable1 : " + data));

        Observable<CarMaker> observable2 =
                Observable
                        .fromArray(SampleData.carMakersDuplicated)
                        .delay(carMaker -> {
                            TimeUtil.sleep(1000L);
                            return Observable.just(carMaker);
                        }).doOnNext(data -> System.out.println(Thread.currentThread().getName() +", doOnNext # observable2 : " + data));


        Observable.sequenceEqual(observable1, observable2)
                .subscribe(data -> System.out.println("onNext : " + data)); // observable 데이터의 순서, 값, 개수가 일치하면 true, 아니면 false
    }
}
