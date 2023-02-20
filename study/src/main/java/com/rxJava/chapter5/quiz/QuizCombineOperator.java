package com.rxJava.chapter5.quiz;

import common.SampleData;
import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class QuizCombineOperator {

    public static void main(String[] args) {
        quiz1();
        answer();
    }

    // zip 을 이용하여 각 지점별 월별매출을 각각의 월별로 합산하여 통합 월별매출을 출력하세요
    static void quiz1() {
        Observable<Integer> a = Observable.fromIterable(SampleData.salesOfBranchA);
        Observable<Integer> b = Observable.fromIterable(SampleData.salesOfBranchB);
        Observable<Integer> c = Observable.fromIterable(SampleData.salesOfBranchC);

        Observable<Integer> month = Observable.range(1, 12);

        Observable.zip(a, b, c, month, ((data1, data2, data3, m) ->
                m + " 월 : 합 = " + (data1 + data2 + data3)))
                .subscribe(data -> System.out.println(data));


    }

    // 깃허브에 올라온 코
    static void answer() {
        List<Observable<Integer>> salesList = Arrays.asList(
                Observable.fromIterable(SampleData.salesOfBranchA),
                Observable.fromIterable(SampleData.salesOfBranchB),
                Observable.fromIterable(SampleData.salesOfBranchC)
        );

        Observable.zip(salesList,
                sale -> (int) sale[0] + (int) sale[1] + (int) sale[2])
                .subscribe(data -> System.out.println(data));
    }
}
