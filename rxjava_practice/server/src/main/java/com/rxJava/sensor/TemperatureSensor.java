package com.rxJava.sensor;

import com.rxJava.utils.NumberUtil;
import com.rxJava.utils.TimeUtil;
import io.reactivex.Observable;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TemperatureSensor {
    // 온도 데이터를 통지하는 스트림을 생성한다.
    public Observable<Integer> getTemperatureStream(){
        return Observable.interval(0L, TimeUnit.MILLISECONDS)
                .delay(item -> {
                    TimeUtil.sleep(NumberUtil.randomRange(1000, 3000));
                    return Observable.just(item);
                })
                .map(notUse -> this.getTemperature());
    }

    private int getTemperature() {
        return NumberUtil.randomRange(-10, 30);
    }
}
