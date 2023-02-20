package com.rxJava.chapter5.chapter0509;

import io.reactivex.Observable;

public class ObservableReduceExample01 {
    public static void main(String[] args) {
        // Observable 이 통지한 데이터를 이용하여, 일정한 방식으로 합성한 후, 최종 결과 반환
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
                .reduce((x, y) -> x + y)
                .subscribe(result -> System.out.println("onNext # 1부터 10까지의 누적 합계: " + result));
    }

    static void func2() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce(0, (x, y) -> { // seed 초기값을 지정
                    System.out.println("# reduce 입력 값 : " + x + ", " + y);
                    return x + y;
                })
                .subscribe(data -> System.out.println("onNext : " + data));
    }

    static void func3() {
        Observable.just("a", "b", "c", "d", "e")
//                .doOnNext(data -> Logger.log(LogType.DO_ON_NEXT, data))
                .reduce((x, y) -> "(" + x + ", " + y + ")")
                .subscribe(data -> System.out.println("onNext : " + data));
    }
}
