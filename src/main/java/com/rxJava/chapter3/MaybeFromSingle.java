package com.rxJava.chapter3;

import io.reactivex.Maybe;
import io.reactivex.Single;

import java.time.LocalDateTime;

public class MaybeFromSingle {
    public static void main(String[] args) {
        Single<LocalDateTime> single = Single.just(LocalDateTime.now());
        Maybe.fromSingle(single)
                .subscribe(
                        data -> System.out.println("data = " + data),
                        error -> System.out.println("error = " + error),
                        () -> System.out.println("complete !")
                );
    }
}
