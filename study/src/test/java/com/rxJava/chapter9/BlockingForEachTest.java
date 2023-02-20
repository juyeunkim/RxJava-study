package com.rxJava.chapter9;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * 생산자가 통지한 데이터를 순차적으로 모두 통지한다.
 * 통지된 각각의 데이터가 모두 조건에 맞아야 true 를 반환한다.
 *
 * BlockingIterable vs BlockingForEach :
 * - BlockingIterable : 각각의 다른 조건을 넣어줄 수 있다
 * - BlockingForEach : 모두 동일한 조건
 * */
public class BlockingForEachTest {

    @Test
    public void getSpeedOfSectionAForEachTest() {
        SampleObservable.getSpeedOfSectionA()
                .filter(speed -> speed > 110)
                .blockingForEach(speed -> assertThat(speed, greaterThan(110)));
    }
}
