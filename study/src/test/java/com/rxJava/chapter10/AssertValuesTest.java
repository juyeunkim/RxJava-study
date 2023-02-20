package com.rxJava.chapter10;

import com.rxJava.chapter9.SampleObservable;
import common.CarMaker;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 통지된 데이터가 한개 이상인 경우에 사용
 * 즉, 통지된 데이터의 값과 순서가 파라미터로 입력된 데이터의 값과 순서와 일치하면 테스트에 성공
 * */
public class AssertValuesTest {

    @Test
    public void getCarMakerAssertValueTest(){
        SampleObservable.getDuplicatedCarMakerStream()
                .filter(carMaker -> carMaker.equals(CarMaker.CHEVROLET))
                .test()
                .awaitDone(10L, TimeUnit.MILLISECONDS)
                .assertValues(CarMaker.CHEVROLET, CarMaker.CHEVROLET);
    }
}
