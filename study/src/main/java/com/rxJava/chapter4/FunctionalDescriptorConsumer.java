package com.rxJava.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class FunctionalDescriptorConsumer {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 6, 10, 30, 65, 70, 102);
        forEachPrint(numbers, n -> System.out.println(n));
    }

    // Consumer: T -> void
    public static <T> void forEachPrint(List<T> numbers, Consumer<T> c) {
        for(T number : numbers)
            c.accept(number);
    }
}
