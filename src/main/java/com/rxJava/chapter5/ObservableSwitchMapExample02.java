package com.rxJava.chapter5;

import common.TimeUtil;
import io.reactivex.Observable;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ObservableSwitchMapExample02 {
    public static class Searcher {
        public Map<String, List<String>> map = new HashMap<>();

        {
            map.put("M", Arrays.asList("Macau", "Malaysia", "Maldives", "Mexico", "Myanmar", "Macedonia"));
            map.put("Ma", Arrays.asList("Macau", "Malaysia", "Maldives", "Macedonia"));
            map.put("Mal", Arrays.asList("Malaysia", "Maldives"));
            map.put("Malay", Arrays.asList("Malaysia"));
        }

        public List<String> search(String keyword){
            List<String> results = map.get(keyword);
            if(results == null){
                results = new ArrayList<>();
            }
            return results;
        }
    }

    public static void main(String[] args) {
        usingConcatMap();
        usingSwitchMap();
    }

    static void usingConcatMap() {
        System.out.println("################### ConcatMap ");
        TimeUtil.start();
        Searcher searcher = new Searcher();
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay"); // 사용자가 입력하는 검색어

        Observable.interval(100L, TimeUnit.MILLISECONDS) // 사용자 입력시간이라 가정
                .take(4)
                .concatMap(data -> { /** concatMap을 사용했기때문에 매번 모든 키워드 검색 결과를  다 가져온다.*/
                    String keyword = keywords.get(data.intValue()); // 데이터베이스에서 조회한다고 가정

                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("================================================================= keyword :" + keyword))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(
                        data -> System.out.println(data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );


        TimeUtil.sleep(6000L);
    }

    static void usingSwitchMap() {
        System.out.println("################### SwitchMap");
        TimeUtil.start();
        Searcher searcher = new Searcher();
        final List<String> keywords = Arrays.asList("M", "Ma", "Mal", "Malay"); // 사용자가 입력하는 검색어

        Observable.interval(100L, TimeUnit.MILLISECONDS) // 사용자 입력시간이라 가정
                .take(4)
                .switchMap(data -> { /** switchMap을 사용했기 때문에 마지막 키워드를 사용한 최신 검색 결과만 가져온다 */
                    String keyword = keywords.get(data.intValue()); // 데이터베이스에서 조회한다고 가정

                    return Observable.just(searcher.search(keyword))
                            .doOnNext(notUse -> System.out.println("================================================================= keyword :" + keyword))
                            .delay(1000L, TimeUnit.MILLISECONDS);
                })
                .flatMap(resultList -> Observable.fromIterable(resultList))
                .subscribe(
                        data -> System.out.println(data),
                        error -> {},
                        () -> {
                            TimeUtil.end();
                            TimeUtil.takeTime();
                        }
                );


        TimeUtil.sleep(6000L);
    }
}
