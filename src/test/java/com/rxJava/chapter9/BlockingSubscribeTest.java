package com.rxJava.chapter9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 통지된 원본 데이터를 호출한 원본 쓰에드에서 부수적인 처리를 할 수 있도록 해준다
 * 소비자가 전달 받은 데이터로 어떤 부수적인 처리 할때 이 처리 결과를 테스트 할 수 있다.
 * */
public class BlockingSubscribeTest {
    @Test
    public void avgTempOfSeoulTest() {
        Calculator calculator = new Calculator();

        SampleObservable.getSalesOfBranchA()
                .blockingSubscribe(data -> calculator.setSum(data));

        assertThat(calculator.getSum(), is(326_000_000));
    }


    public class Calculator {
        private Integer sum;

        Calculator() {
            this.sum = 0;
        }

        void setSum(Integer num) {
            this.sum += num;
        }

        Integer getSum() {
            return sum;
        }
    }
}
