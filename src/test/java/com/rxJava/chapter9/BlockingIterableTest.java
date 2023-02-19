package com.rxJava.chapter9;

import common.CarMaker;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 생산자가 통지한 모든 데이터를 받을 수 있는 Iterable 을 얻게 한다
 * 구독 후, Iterator 의 next() 메서드를 호출하는 시점부터 처리한다.
 * */
public class BlockingIterableTest {
    @Test
    public void getCarMakerIterableTest() {
        // when
        Iterable<CarMaker> iterable = SampleObservable.getCarMakerStream()
                .blockingIterable();

        Iterator<CarMaker> iterator;
        iterator = iterable.iterator();

        // then
        assertThat(iterator.hasNext(), is(true));

        assertThat(iterator.next(), is(CarMaker.CHEVROLET));
        assertThat(iterator.next(), is(CarMaker.HYUNDAI));
        assertThat(iterator.next(), is(CarMaker.SAMSUNG));
        assertThat(iterator.next(), is(CarMaker.SSANGYOUNG));
        assertThat(iterator.next(), is(CarMaker.KIA));

    }

}
