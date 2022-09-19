package com.rxJava.chapter5;

import common.SampleData;
import io.reactivex.Observable;

public class ObservableToListExample02 {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .toList()
                .subscribe(carList -> System.out.println(carList));
    }
}
