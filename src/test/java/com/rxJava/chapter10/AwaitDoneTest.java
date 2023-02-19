package com.rxJava.chapter10;

import io.reactivex.Observable;
import org.junit.Test;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 파라미터로 지정된 시간 동안 대기시키거나 지정된 시간 전에 완료 통지나 에러통지가 있다면 통지가 있을때까지만 대기시킨다.
 * */
public class AwaitDoneTest {
    // 지정된 시간까지 완료 통지가 없이, 해당 시점까지 전달 받은 데이터의 개수가 맞는지 검증하는 예제
    @Test
    public void awaitDoneTest01() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitDone(500L, TimeUnit.MILLISECONDS)
                .assertNotComplete()
                .assertValueCount(2);
    }

    // 지정된 시간 전에 완료 통지가 있어, 완료 통지 시점까지만 대기하고 전달 받은 데이터의 개수가 맞는지 검증하는 예제
    @Test
    public void awaitDoneTest02() {
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .awaitDone(1500L, TimeUnit.MILLISECONDS)
                .assertComplete()
                .assertValueCount(5);
    }
}
