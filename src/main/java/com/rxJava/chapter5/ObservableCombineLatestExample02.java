package com.rxJava.chapter5;

import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;
import utils.NumberUtil;

import java.util.concurrent.TimeUnit;

public class ObservableCombineLatestExample02 {
    public static void main(String[] args) {
        // 랜덤 온도 데이터
        Observable<Integer> observable1 = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.temperatureOfSeoul[NumberUtil.randomRange(0, 5)]);

        // 랜덤 습도 데이터
        Observable<Integer> observable2 = Observable.interval(NumberUtil.randomRange(100, 500), TimeUnit.MILLISECONDS)
                .take(10)
                .map(notUse -> SampleData.humidityOfSeoul[NumberUtil.randomRange(0, 5)]);

        Observable.combineLatest(observable1, observable2,
                (temperature, humidity) -> "최신 온습도 데이터 - 온도: " + temperature + "도\t습도: " + humidity + "%")
                .subscribe(data -> System.out.println(data));

        TimeUtil.sleep(3000L);
    }
}
