package com.rxJava.chapter9;

import common.CarMaker;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnitTestNotByRxJava {
    @Test
    public void getCarMakerStreamSyncTest(){
        List<CarMaker> carMakerList = new ArrayList<>();
        SampleObservable.getCarMakerStream()
                .subscribe(data -> carMakerList.add(data));

        // 결과는 실패
        // why? -> SampleObservable.getCarMakerStream() 이 다른 스레드에서 돌고있음.
        assertThat(carMakerList.size(), is(5));
    }
}
