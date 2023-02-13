package com.rxJava.chapter9;

import io.reactivex.Observable;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * 생산자가 0개 또는 1개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다
 * - 0 개 이면 null 을 리턴
 * 생산자가 Maybe 일 경우 사용
 * */
public class BlockingGetTest {
    @Test
    public void blockingGetEmptyTest(){
        // then
        assertThat(Observable.empty().firstElement().blockingGet(), is(nullValue()));

        // firstElement() : Maybe
    }

    @Test
    public void totalSalesOfBranchATest(){
        // when
        int totalSales = SampleObservable.getSalesOfBranchA()
                .reduce((a, b) -> a + b)
                .blockingGet();

        // then
        assertThat(totalSales, is(326_000_000));
    }

    @Test
    public void salesAllBranchTest(){
        // when
        int totalSales = Observable.zip(
                SampleObservable.getSalesOfBranchA(),
                SampleObservable.getSalesOfBranchB(),
                SampleObservable.getSalesOfBranchC(),
                (a, b, c) -> a + b + c
        )
                .reduce((a, b) -> a + b)
                .blockingGet();

        // then
        assertThat(totalSales, is(992_000_000));

    }
}
