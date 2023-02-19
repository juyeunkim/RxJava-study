package com.rxJava.chapter10;

import com.rxJava.chapter9.SampleObservable;
import common.Car;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 테스트 시점까지 통지받은 데이터가 없다면 테스트에 성공
 * Observable.empty() 로 생성시, 완료를 통지하기 때문에 테스트가 실패한다.
 * 즉, 통지 이벤트 자체가 없는지를 테스트할 수 있다.
 * */
public class AssertEmptyTest {

    // 테스트 실패 예제
    @Test
    public void getCarStreamEmptyFailTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.test();

        // then
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
        // 이미 데이터가 통지되었기때문에 실패.
    }

    // 테스트 성공 예제
    @Test
    public void getCarStreamEmptySuccessTest(){
        // when
        Observable<Car> observable = SampleObservable.getCarStream();
        TestObserver<Car> observer = observable.delay(1000L, TimeUnit.MILLISECONDS).test();

        // then
        observer.awaitDone(100L, TimeUnit.MILLISECONDS).assertEmpty();
    }

}
