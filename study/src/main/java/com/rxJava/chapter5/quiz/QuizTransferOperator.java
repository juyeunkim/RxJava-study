package com.rxJava.chapter5.quiz;

import io.reactivex.Observable;

public class QuizTransferOperator {
    public static void main(String[] args) {
        quiz1();
        quiz2();
    }

    // range, filter, map 을 이용하여 1부터 15까지의 숫자 중에 2의 배수만 필터링 한 후, 필터링된 숫자에 제곱한 숫자를 출력하세요.
    static void quiz1() {
        System.out.println("## QUIZ 1");
        Observable.range(1, 15)
                .filter(n -> n % 2 == 0)
                .map(n -> "n = " + n + ", n * n = " + n * n)
                .subscribe(result -> System.out.println(result))
                ;
    }

    // range filter, flatMap 을 이용하여 2에서 9까지의 구구단 중에서 짝수단만 출력하세요.
    static void quiz2() {
        System.out.println("## QUIZ 2");
        Observable.range(2, 8)
                .filter(n -> n % 2 == 0)
                .flatMap(num -> Observable.range(1, 9)
                        .map(row -> num + " * " + row + " = " + num * row))
                .subscribe(result -> System.out.println(result))
                ;

    }
}
