package com.rxJava.chapter5;

import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableConcatExample02 {
    public static void main(String[] args) {
        List<Observable<String>> speedPerSectionList = Arrays.asList(
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA),
                SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB),
                SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC)
        );

        Observable.concat(speedPerSectionList)
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(2000L);
    }
}
