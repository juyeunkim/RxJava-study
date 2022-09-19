package com.rxJava.chapter5.quiz;

import common.SampleData;
import io.reactivex.Observable;

public class QuizTransferOperator2 {
    public static void main(String[] args) {
        quiz1();
    }

    // toMap 을 이용하여 SampleData.carList 의 car 객체들을 carName 을 key 로, carMaker 를 value 로 가지는 Map 으로 출력하세요.
    static void quiz1() {
        System.out.println("## QUIZ 1");
        Observable.fromIterable(SampleData.carList)
                .toMap(data -> data.getCarName(), data -> data.getCarMaker())
                .subscribe(result -> System.out.println(result));
    }
}
