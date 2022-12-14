package com.rxJava.chapter6;

import io.reactivex.subjects.PublishSubject;

/**
 * 소비자가 구독한 시점 이 후에 통지 된 데이터만 소비자에게 전달되는 PublishSubject 예제
 */
public class PublishSubjectExample {
    public static void main(String[] args){
        PublishSubject<Integer> subject = PublishSubject.create(); // HotPublisher

        subject.subscribe(price -> System.out.println("onNext # 소비자 1 : " + price));
        subject.onNext(3500);
        subject.subscribe(price -> System.out.println("onNext # 소비자 2 : " + price));
        subject.onNext(3300);
        subject.subscribe(price -> System.out.println("onNext # 소비자 3 : " + price));
        subject.onNext(3400);

        // 소바자 4 는 한개도 전달받지 못함
        subject.subscribe(
                price -> System.out.println("onNext # 소비자 4 : " + price),
                error -> System.out.println("onError " + error),
                () -> System.out.println("onComplete")
        );

        subject.onComplete();


    }
}
