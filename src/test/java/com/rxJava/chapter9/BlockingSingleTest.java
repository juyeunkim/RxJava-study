package com.rxJava.chapter9;

import io.reactivex.Observable;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * 생자가 한 개의 데이터를 통지하고 완료되면 해당 데이터를 반환한다
 * 2개 이상의 데이터를 통지할 경우에는 illegalArgumentException 발생
 * */
public class BlockingSingleTest {
    @Test
    public void totalSalesOfBranchATest(){
        int actual = SampleObservable.getSalesOfBranchA()
                .filter(sale -> sale > 30_000_000)
                .take(1)
                .blockingSingle();

        assertThat(actual, is(35_000_000));
    }


    @Test(expected = IllegalArgumentException.class)
    public void totalSalesOfBranchATest2(){
        SampleObservable.getSalesOfBranchA()
                .filter(sale -> sale > 30_000_000)
                .take(2) // 2개 이상의 데이터를 통지하기 때문에 에러 발생
                .blockingSingle();
    }

    // 0개를 가져오면 NoSuchElementException
    @Test(expected = NoSuchElementException.class)
    public void totalSalesOfBranchATest3() {
        assertThat(Observable.empty().blockingSingle(), is(nullValue()));
    }
}
