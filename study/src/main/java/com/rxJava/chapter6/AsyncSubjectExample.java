package com.rxJava.chapter6;

import io.reactivex.subjects.AsyncSubject;

/**
 * 구독 시점에 상관없이 모든 소비자들이 마지막으로 통지된 데이터만 전달 받는 AsyncSubject 예제
 */
public class AsyncSubjectExample {

    public static void main(String[] args){
        AsyncSubject<Integer> subject = AsyncSubject.create();
        subject.onNext(1000);

        subject.doOnNext(price -> System.out.println("doOnNext # 소비자 1 : " + price))
                .subscribe(price -> System.out.println("onNext # 소비자 1 : " + price));
        subject.onNext(2000);

        subject.doOnNext(price -> System.out.println("doOnNext # 소비자 2 : " + price))
                .subscribe(price -> System.out.println("onNext # 소비자 2 : " + price));
        subject.onNext(3000);

        subject.doOnNext(price -> System.out.println("doOnNext # 소비자 3 : " + price))
                .subscribe(price -> System.out.println("onNext # 소비자 3 : " + price));
        subject.onNext(4000);

        subject.onComplete(); // 소비자1,2,3 뿐만 아니라 4도 가장 마지막에 받은 4000 값을 받음

        subject.doOnNext(price -> System.out.println("doOnNext # 소비자 4 : " + price))
                .subscribe(price -> System.out.println("onNext # 소비자 4 : " + price));
    }
}
