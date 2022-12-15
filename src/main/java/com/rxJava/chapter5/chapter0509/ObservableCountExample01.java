package com.rxJava.chapter5.chapter0509;

import common.SampleData;
import io.reactivex.Observable;

import java.util.Arrays;

public class ObservableCountExample01 {

    public static void main(String[] args) {
        // count 통지한 데이터의 총 개수
        // 총 개수를 통지하는 시점은 완료 통지를 받은 시점
        func1();
        func2();
    }

    static void func1() {
        Observable.fromIterable(SampleData.carList)
                .count()
                .subscribe(data -> System.out.println("onNext : " + data));
    }

    static void func2() {
        Observable.concat(
                Arrays.asList(
                        Observable.fromIterable(SampleData.seoulPM10List),
                        Observable.fromIterable(SampleData.busanPM10List),
                        Observable.fromIterable(SampleData.incheonPM10List)
                )
        )
                .count()
                .subscribe(data-> System.out.println("onNext : " + data));
    }
}
