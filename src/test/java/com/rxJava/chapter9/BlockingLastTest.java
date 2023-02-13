package com.rxJava.chapter9;

import common.Car;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 생산자가 통지한 마지막 데이터를 반환
 * 통지된 데이터가 없을 경우 NoSuchElementException 발생
 * 결과를 반환하는 시점이 완료를 통지하는 시점이므로 완료 통지가 없는 데이터 통지일 경우 사용할 수 없다.
 * */
public class BlockingLastTest {

    @Test
    public void getCarStreamLastTest(){
        // when
        Car car = SampleObservable.getCarStream().blockingLast();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("SM5"));
    }

    @Test
    public void getSalesOfBranchALastTest(){
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                .take(6)
                .blockingLast();

        // then
        assertThat(actual, is(40_000_000));
    }
}
