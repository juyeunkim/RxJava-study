package com.rxJava.chapter5;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

public class ObservableToMapExample01 {
    public static void main(String[] args) {
        Single<Map<String, String>> single =
                Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo") // map 의 value
                        .toMap(data -> data.split("-")[0]); // map 의 key

        single.subscribe(map -> System.out.println(map));
    }
}
