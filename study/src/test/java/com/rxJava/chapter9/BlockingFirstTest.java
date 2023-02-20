package com.rxJava.chapter9;

import common.Car;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 통지되는 첫번째 데이터를 테스트
 * 통지된 데이터가 없을 경우 NoSuchElementException 발생
 * */
public class BlockingFirstTest {

    @Test
    public void getCarStreamFirstTest(){
        // when
        Car car = SampleObservable.getCarStream().blockingFirst();
        String actual = car.getCarName();

        // then
        assertThat(actual, is("말리부"));
    }

    @Test
    public void getSalesOfBranchAFirstTest(){
        // when
        int actual = SampleObservable.getSalesOfBranchA()
                .take(6)
                .blockingFirst();

        // then
        assertThat(actual, is(15_000_000));
    }
}
