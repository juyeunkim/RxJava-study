package com.rxJava.chapter10;

import io.reactivex.Observable;
import org.junit.Test;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 생산자쪽에서 완료 통지나 에러 통지가 있을때까지 스레드를 대기시킨다.
 * 파라미터로 지정된 시간동안 대기하며, 대기 시간내에 완료 통지가 있었는지 여부를 검증한다.
 * */
public class AwaitTest {
    // 생산자쪽에서 완료 통지를 보낼때까지 대기한 후, 완료 및 통지된 데이터 개수를 검증하는 예제
    @Test
    public void awaitTest() throws InterruptedException {
        Observable.interval(100L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .await()
                .assertComplete()
                .assertValueCount(5);
    }

    // 지정한 시간동안 대기하면서 대기 시간내에 완료 통지를 받았는지 여부를 검증하는 예제
    @Test
    public void awaitTest02() throws InterruptedException {
        boolean result = Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .take(5)
                .doOnComplete(() -> Logger.log(LogType.DO_ON_COMPLETE))
                .doOnError(error -> Logger.log(LogType.DO_ON_ERROR, error.getMessage()))
                .test()
                .await(2000L, TimeUnit.MILLISECONDS);

        assertThat(result, is(false));
    }
}
