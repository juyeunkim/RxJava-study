package com.rxJava.chapter5;

import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.Map;

public class ObservableToMapExample02 {
    public static void main(String[] args) {
        Single<Map<String, String>> single = Observable.just("a-Alpha", "b-Bravo", "c-Charlie", "e-Echo")
                .toMap(
                        data -> data.split("-")[0], // key
                        data -> data.split("-")[1] // value
                );

        single.subscribe(map -> System.out.println(map));
    }
}
