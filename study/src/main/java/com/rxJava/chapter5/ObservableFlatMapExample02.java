package com.rxJava.chapter5;

import io.reactivex.Observable;

public class ObservableFlatMapExample02 {
    public static void main(String[] args) {
        // 기존 이중포문으로 작성하는 내용을 range + flapMap 으로 사용가능
        Observable.range(2, 1)
                .flatMap(
                        num -> Observable.range(1, 9)
                                .map(row -> num + " * " + row + " = " + num * row)
                )
                .subscribe(data -> System.out.println(data));
    }
}
