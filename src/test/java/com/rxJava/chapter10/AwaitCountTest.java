package com.rxJava.chapter10;

import io.reactivex.Observable;
import org.junit.Test;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 파라미터로 지정된 개수만큼 통지될 때 까지 쓰레드를 대기시킨다.
 * */
public class AwaitCountTest {
    // 지정된 개수만큼 대기하고 완료 통지 유무, 통지된 데이터 개수 및 데이터의 값과 순서를 검증하는 예제
    @Test
    public void awaitCountTest() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitCount(3)
                .assertNotComplete()
                .assertValueCount(3)
                .assertValues(0L, 1L, 2L);
    }
}
