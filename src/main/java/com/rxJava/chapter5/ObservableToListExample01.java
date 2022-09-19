package com.rxJava.chapter5;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public class ObservableToListExample01 {
    public static void main(String[] args) {
        Single<List<Integer>> single = Observable.just(1, 3, 5, 7, 9)
                .toList();

        single.subscribe(data -> System.out.println(data)); // 하나의 리스트안에 데이트가 담겨서 출력
    }
}
