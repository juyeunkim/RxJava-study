package com.rxJava.chapter5.chapter0508;

import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ObservableAmbExample {
    public static void main(String[] args) {

        List<Observable<Integer>> observables = Arrays.asList(
                Observable.fromIterable(SampleData.salesOfBranchA)
                        .delay(200L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> System.out.println("doOnComplete, # branch A's sales")),
                Observable.fromIterable(SampleData.salesOfBranchB)
                        .delay(300L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> System.out.println("doOnComplete, # branch B's sales")),
                Observable.fromIterable(SampleData.salesOfBranchC)
                        .delay(500L, TimeUnit.MILLISECONDS)
                        .doOnComplete(() -> System.out.println("doOnComplete, # branch C's sales"))
        );

        Observable.amb(observables) // 최초 통지 시점이 가장빠른 Observable 의 데이터만 통지되고, 나머지는 무시
                .doOnComplete(() -> System.out.println("doOnComplete, 완료"))
                .subscribe(data -> System.out.println("data : " + data));

        TimeUtil.sleep(1000L);
    }
}
