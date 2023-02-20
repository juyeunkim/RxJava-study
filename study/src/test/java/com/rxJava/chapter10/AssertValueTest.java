package com.rxJava.chapter10;

import com.rxJava.chapter9.SampleObservable;
import common.CarMaker;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class AssertValueTest {
    @Test
    public void assertValueTest(){
        Observable.just("a")
                .test()
                .assertValue("a");
    }

    @Test
    public void getCarMakerAssertValueTest(){
        SampleObservable.getCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.SAMSUNG))
                .test()
                .awaitDone(10L, TimeUnit.MILLISECONDS) // SampleObservable.getCarMakerStream() 에서 전달하는 스레드가 다르기 때문에 사용
                .assertValue(CarMaker.SAMSUNG);
    }
}
