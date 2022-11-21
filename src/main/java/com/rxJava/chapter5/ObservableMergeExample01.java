package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class ObservableMergeExample01 {
    public static void main(String[] args) {
        Observable<Long> observable1 = Observable.interval(200L, TimeUnit.MILLISECONDS)
                .take(5);

        Observable<Long> observable2 = Observable.interval(400L, TimeUnit.MILLISECONDS)
                .take(5)
                .map(num -> num + 1000);

        Observable.merge(observable1, observable2) // 통지 시점이 겹쳤을때 : merge 함수의 param 에 먼저 등록된 Observable 의 데이터 통지
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(4000);
    }
}
