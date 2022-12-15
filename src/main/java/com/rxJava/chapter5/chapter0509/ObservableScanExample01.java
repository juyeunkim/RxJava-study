package com.rxJava.chapter5.chapter0509;

import io.reactivex.Observable;

public class ObservableScanExample01 {
    public static void main(String[] args) {
        System.out.println("=============================");
        func1();
        System.out.println("=============================");
        func2();
        System.out.println("=============================");
        func3();

    }

    static void func1() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .doOnNext(data -> System.out.println("doOnNext : " + data))
                .scan((x, y) -> x + y) // 첫번째값은 하나만 통지, 데이터가 통지될때마다 합성된 데이터 방출
                .subscribe(result -> System.out.println("onNext : " + "# 1부터 10까지의 누적 합계: " + result));
    }

    static void func2() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce(0, (x, y) -> {
                    System.out.println("# reduce 입력 값 : " + x + ", " + y);
                    return x + y;
                })
                .subscribe(data -> System.out.println("onNext : " + data));
    }

    static void func3(){
        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .scan((x, y) -> "(" + x + ", " + y + ")")
                .subscribe(result -> System.out.println("출력 결과 : " + result));
    }
}
