package com.rxJava.chapter5;

import io.reactivex.Observable;

public class ObservableFlatMapExample03 {
    public static void main(String[] args) {
        // ObservableFlatMapExample02 에서는 flatMap 내부에서 map 을 이용해서 데이터를 가공 -> 소비자에 데이터 리턴
        // ObservableFlatMapExample03 에서는 flatMap 에서 데이터를 가공 -> 소비자에 데이터 리턴

        Observable.range(2, 1) // sourceData
                .flatMap(
                        data -> Observable.range(1, 9), // transformedData
                        (sourceData, transformedData) ->
                                sourceData + " * " + transformedData + " = " + sourceData * transformedData
                )
                .subscribe(data -> System.out.println(data));
    }
}
