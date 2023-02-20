package com.rxJava.chapter10;

import com.rxJava.chapter9.SampleObservable;
import common.TimeUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 해당 시점에 통지가 완료되면 성공
 * */
public class AssertCompleteTest {

    @Test
    public void assertCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith(
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> {
                            TimeUtil.sleep(100L); // 12 * 0.1 = 1.2 초 소요
                            return a + b;
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MILLISECONDS)
                .assertComplete();
    }
}
