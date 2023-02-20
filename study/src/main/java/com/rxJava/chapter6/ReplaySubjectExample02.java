package com.rxJava.chapter6;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample02 {
    public static void main(String[] args){
        ReplaySubject<Integer> subject = ReplaySubject.createWithSize(2); // 가장 최근에 통지된 데이터 2개를 받는다.
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> System.out.println("onNext # 소비자 1 : " + price));
        subject.onNext(3500);

        // 2500 부터
        subject.subscribe(price -> System.out.println("onNext # 소비자 2 : " + price));
        subject.onNext(3300);

        // 3500 부터
        subject.subscribe(price -> System.out.println("onNext # 소비자 3 : " + price));
        subject.onNext(3400);

        subject.onComplete();

        // 3300 부터
        subject.subscribe(price -> System.out.println("onNext # 소비자 4 : " + price));
    }
}
