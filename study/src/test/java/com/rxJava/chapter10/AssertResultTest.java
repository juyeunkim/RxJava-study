package com.rxJava.chapter10;

import io.reactivex.Observable;
import org.junit.Test;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 해당 시점까지 통지를 완료했고, 통지된 데이터와 파라미터로 입력된 데이터의 값과 순서가 같으면 테스트에 성공한다.
 * assertValues 와의 차이점은 해당 시점까지 완료 통지를 받았느냐 받지 않았느냐이다.
 * */
public class AssertResultTest {

    // 테스트 실패 예제
    @Test
    public void assertResultFailTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .filter(data -> data > 3)
                .test()
                .awaitDone(1100L, TimeUnit.MILLISECONDS)
                .assertResult(4L);
                // 실패 발생 원인 : interval 로 계속해서 데이터를 통지하고있음 (완료 통지 x)
                // assertResult 는 통지를 완료하고, 통지한 데이터와 값의 순서가 같은지를 확인
    }

    // 테스트 성공 예제
    @Test
    public void assertResultSuccessTest(){
        Observable.interval(200L, TimeUnit.MILLISECONDS)
                .doOnNext(data -> Logger.log(LogType.ON_NEXT, data))
                .take(5) // 5개의 데이터를 통지후 완료처리
                .filter(data -> data > 3)
                .test()
                .awaitDone(1100L, TimeUnit.MILLISECONDS)
                .assertResult(4L);
    }
}
