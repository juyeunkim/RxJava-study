package com.rxJava.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionalDescriptorFunctionExample {
    public static void main(String[] args) {
        List<Character> characterList = Arrays.asList('a', 'b', 'c', 'd', 'e');
        List<Integer> asciiNumbers = map(characterList, character -> (int) character);

        System.out.println(asciiNumbers.size()); // 5
        for(int asciiNumber : asciiNumbers)
            System.out.println(asciiNumber);
    }

    // Function T -> R
    public static <T, R> List<R> map(List<T> list, Function<T, R> f){
        List<R> result = new ArrayList<>();
        for(T t : list)
            result.add(f.apply(t));
        return result;
    }
}
