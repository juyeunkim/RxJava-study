package com.rxJava.chapter6;

import io.reactivex.subjects.ReplaySubject;

public class ReplaySubjectExample01 {
    public static void main(String[] args){
        ReplaySubject<Integer> subject = ReplaySubject.create();
        // 데이터의 개수를 지정하지 않아서 구독 시점에 통지된 데이터가 있다면 모두 받을 수 있다.
        subject.onNext(3000);
        subject.onNext(2500);

        subject.subscribe(price -> System.out.println("onNext # 소비자 1 : " + price));
        subject.onNext(3500);

        subject.subscribe(price -> System.out.println("onNext # 소비자 2 : " + price));
        subject.onNext(3300);

        subject.onComplete();

        // 구독이 완료되었더라도 모든 데이터를 받는다.
        subject.subscribe(price -> System.out.println("onNext # 소비자 3 : " + price));

    }
}
