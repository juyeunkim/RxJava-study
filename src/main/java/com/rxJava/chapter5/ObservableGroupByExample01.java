package com.rxJava.chapter5;

import common.Car;
import common.CarMaker;
import common.SampleData;
import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class ObservableGroupByExample01 {
    public static void main(String[] args) {
        Observable<GroupedObservable<CarMaker, Car>> observable =
                Observable.fromIterable(SampleData.carList).groupBy(car -> car.getCarMaker());

        observable.subscribe(
                groupedObservable -> groupedObservable.subscribe(
                        car -> System.out.println("Group " + groupedObservable.getKey() + " Car name :" + car.getCarName())
                )
        );

    }
}
