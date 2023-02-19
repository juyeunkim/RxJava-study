package com.rxJava.chapter10;

import io.reactivex.Observable;
import org.junit.Test;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 해당 시점까지 통지된 데이터가 없으면 테스트에 성공
 * 완료 통지와 에러 통지는 테스트 대상에서 제외
 * */
public class AssertNoValuesTest {
    @Test
    public void assertNoValuesTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .filter(data -> data > 5)
                .test()
                .awaitDone(1000L, TimeUnit.MILLISECONDS)
                .assertNoValues();
    }
}
