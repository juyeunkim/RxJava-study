package com.rxJava.chapter5.chapter0509.quiz;

import io.reactivex.Observable;

public class Quiz {

    // range, reduce 함수를 이용하여 10부터 1까지 역순으로 뺄셈을 한 최종결과 값을 구하세요
    public static void main(String[] args) {

        // 1 -> 10
        // 2 -> 9
        // 3 -> 8
        // 4 -> 7 ....
        Observable.range(1, 10)
                .map(data -> (11 - data) * -1)
                .doOnNext(data -> System.out.println("doOnNext : " + data))
                .reduce(0, (x,y) -> {
                    System.out.println("x :" + x + ", y : " + y);
                    return x + y;
                })
                .subscribe(result -> System.out.println("result : " + result))
                ;

    }
}
