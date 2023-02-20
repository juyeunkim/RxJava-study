package com.rxJava.chapter5;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableFromIterableExample {
    public static void main(String[] args){
        List<String> countries = Arrays.asList("Korea", "Canada", "USA", "Italy");

        Observable.fromIterable(countries)
                .subscribe(country -> System.out.println(country));
    }
}
