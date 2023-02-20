package com.rxJava.chapter5;

import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;

public class ObservableMergeExample02 {
    public static void main(String[] args) {
        Observable<String> observable1 =
                SampleData.getSpeedPerSection("A", 55L, SampleData.speedOfSectionA);
        Observable<String> observable2 =
                SampleData.getSpeedPerSection("B", 100L, SampleData.speedOfSectionB);
        Observable<String> observable3 =
                SampleData.getSpeedPerSection("C", 77L, SampleData.speedOfSectionC);
 
        Observable.merge(observable1, observable2, observable3)
                .subscribe(data -> System.out.println(data));


        TimeUtil.sleep(1000L);
    }
}
