package com.rxJava.chapter5;

import io.reactivex.Observable;

public class ObservableFlatMapExample01 {
    public static void main(String[] args) {
        Observable.just("Hello")
                .flatMap(hello -> Observable.just("자바", "파이썬", "안드로이드") // flapMap 내에서 새로운 Observable 을 생성하여 return
                                        .map(lang -> hello + ", " + lang))
                .subscribe(data -> System.out.println(data));
    }
}
