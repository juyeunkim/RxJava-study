package com.rxJava.chapter5.chapter0508;

import common.CarMaker;
import common.SampleData;
import io.reactivex.Observable;

public class ObservableAllExample {
    public static void main(String[] args) {
        Observable.fromIterable(SampleData.carList)
                .doOnNext(car ->
                                System.out.println("Car Maker : " + car.getCarMaker() + ", \tCar Name: " + car.getCarName()))
                .map(car -> car.getCarMaker())
                .all(carMaker -> carMaker.equals(CarMaker.CHEVROLET)) // 중간에 일치하지 않는 조건이있다면, 방출 해제
//                .all(CarMaker.CHEVROLET::equals)
                .subscribe(data -> System.out.println("data : " + data));
    }
}
