package com.rxJava.chapter8;

import common.CarMaker;
import common.SampleData;
import common.TimeUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import utils.LogType;
import utils.Logger;

import java.util.concurrent.TimeUnit;

/**
 * 소비자가 구독을 해지하는 시점에, 지정된 작업을 처리할 수 있다
 * 완료나 에러로 종료 될 경우에는 실행되지 않는다.
 *
 * doFinally : 구독이 취소 된 후, 완료 또는 에러가 통지된 후 호출되는 함수 (doOnDispose/doOnCancel + doOnComplete + doOnError)
 * doOnLifecycle : 소비자가 구독할 때 또는 구독 해지할 때 호출되는 함수 (doOnSubscribe + doOnDispose/doOnCancel)
 * */
public class DoOnDisposeExample {
    public static void main(String[] args) {
        Observable.fromArray(SampleData.carMakers)
                .zipWith(Observable.interval(300L, TimeUnit.MILLISECONDS), (carMaker, num) -> carMaker)
                // 구독 해지가 호출되고나서 출력
                .doOnDispose(() -> Logger.log(LogType.DO_ON_DISPOSE, "# 생산자: 구독 해지 완료"))
                .subscribe(new Observer<CarMaker>() {
                    private Disposable disposable;
                    private long startTime;
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        this.disposable = disposable;
                        this.startTime = TimeUtil.start();
                    }

                    @Override
                    public void onNext(CarMaker carMaker) {
                        Logger.log(LogType.ON_NEXT, carMaker);
                        if(TimeUtil.getCurrentTime() - startTime > 1000L){
                            Logger.log(LogType.PRINT, "# 소비자: 구독 해지 , 1000L 초과");
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Logger.log(LogType.ON_ERROR, error);
                    }

                    @Override
                    public void onComplete() {
                        Logger.log(LogType.ON_COMPLETE);
                    }
                });


        TimeUtil.sleep(2000L);
    }
}
