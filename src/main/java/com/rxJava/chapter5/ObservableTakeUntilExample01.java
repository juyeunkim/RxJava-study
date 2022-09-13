package com.rxJava.chapter5;

import io.reactivex.Observable;

import java.util.Arrays;

public class ObservableTakeUntilExample01 {
    // 파라미터로 지정한 조건이 될때까지 데이터를 발행
    public static void main(String[] args) {
        Observable.fromIterable(Arrays.asList(1,2,3,4,5,6,7,8,9,10))
                .takeUntil(i -> i == 5)
                .subscribe(i -> System.out.println(i))
        ;
    }
}
