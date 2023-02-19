package com.rxJava.chapter10;

import com.rxJava.chapter9.SampleObservable;
import common.TimeUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 해당 시점까지 완료 통지가 없으면 테스트에 성공 (ex. 에러 발생,
 * */
public class AssertNotCompleteTest {

    @Test
    public void assertNotCompleteTest() {
        SampleObservable.getSalesOfBranchA()
                .zipWith(
                        SampleObservable.getSalesOfBranchB(),
                        (a, b) -> {
                            TimeUtil.sleep(1000L); // 12 * 1 = 12초 소요
                            return a + b;
                        }
                )
                .test()
                .awaitDone(3000L, TimeUnit.MILLISECONDS)
                .assertNotComplete();
    }
}
