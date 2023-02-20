package com.rxJava.chapter6;

import io.reactivex.subjects.BehaviorSubject;

/**
 * 구독 시점에 이미 통지된 데이터가 있다면 이미 통지된 데이터의 마지막(최신) 데이터를 전달 받은 후,
 * 구독 이후부터 통지 된 데이터를 전달 받는 예제
 */
public class BehaviorSubjectExample {
    public static void main(String[] args){
        BehaviorSubject<Integer> subject = BehaviorSubject.createDefault(3000);

        subject.subscribe(price -> System.out.println("onNext # 소비자 1 : " + price));
        subject.onNext(3500);

        // 3500 부터 시작
        subject.subscribe(price -> System.out.println("onNext # 소비자 2 : " + price));
        subject.onNext(3300);

        // 3300 부터 시작
        subject.subscribe(price -> System.out.println("onNext # 소비자 3 : " + price));
        subject.onNext(3400);

        subject.onComplete();

        // 구독이 완료되었으므로 소비자 4는 아무값도 얻지 못함
        subject.subscribe(price -> System.out.println("onNext # 소비자 4 : " + price));
    }
}
