package com.rxJava.chapter5.chapter0507;

import common.TimeUtil;
import io.reactivex.Observable;
import utils.NumberUtil;

public class ObservableTimeIntervalExample {
    public ObservableTimeIntervalExample() {
    }

    public static void main(String[] args) {
        Observable.just(1, 3, 5, 7, 9)
                .delay(item -> {
                    TimeUtil.sleep(NumberUtil.randomRange(100, 1000));
                    return Observable.just(item);
                })
                .timeInterval() // 각각의 데이터가 통지되는데 걸리는 시간 + 데이터 Timed<T>
                .subscribe(
                        timed -> System.out.println("# 통지하는데 걸린 시간: " + timed.time() + "\t# 통지된 데이터: " + timed.value())
                );
    }
}
