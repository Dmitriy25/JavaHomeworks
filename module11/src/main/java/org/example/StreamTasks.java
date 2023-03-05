package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {
    public static List<String> names = new ArrayList<>(Arrays.asList("Ivan", "Olha", "Peter", "Jack", "Steve", "Ben", "Ann"));
    public static String[] numbers = {"1, 2, 0", "4, 5"};

    public static String method1(List<String> list) {
        return list.stream()
                .filter(name -> list.indexOf(name) % 2 != 0)
                .map(name -> list.indexOf(name) + "." + name)
                .collect(Collectors.joining(", "));
    }

    public static List<String> method2(List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static String method3(String[] numbers) {
        return Stream.of(numbers)
                .flatMap(subNumbers -> Stream.of(subNumbers.split(", ")))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.joining(", "));
    }

    public static Stream<Long> method4(long a, long c, long m) {
        return Stream.iterate(0L, seed -> (a * seed + c) % m);
    }

    public static <T> Stream<T> method5(Stream<T> first, Stream<T> second) {
        List<T> result = new ArrayList<>();
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            result.add(iterator1.next());
            result.add(iterator2.next());
        }
        return result.stream();
    }
}
