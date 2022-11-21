package com.rxJava.chapter5;

import common.SampleData;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.Collections;

public class ObservableZipExample02 {
    public static void main(String[] args) {
        Observable<Integer> observable1 = Observable.fromIterable(SampleData.seoulPM10List);
        Observable<Integer> observable2 = Observable.fromIterable(SampleData.busanPM10List);
        Observable<Integer> observable3 = Observable.fromIterable(SampleData.incheonPM10List);

        Observable<Integer> observable4 = Observable.range(1, 24);

        Observable.zip(observable1, observable2, observable3, observable4,
                // observable 1~3 에서 미세먼지 농도값이 가장큰 값을 시간별로 나타냄
                (data1, data2, data3, hour) -> hour + "시: " + Collections.max(Arrays.asList(data1, data2, data3)))
                .subscribe(data -> System.out.println(data));
    }
}
