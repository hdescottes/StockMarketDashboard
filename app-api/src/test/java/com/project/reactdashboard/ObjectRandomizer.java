package com.project.reactdashboard;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class ObjectRandomizer {

    public static int randomInt() {
        return nextInt();
    }

    public static String randomString() {
        return randomAlphabetic(10);
    }

    public static <T> List<T> randomList(final Function<Integer, T> mapper) {
        return IntStream.range(0, 5)
                .boxed()
                .map(mapper)
                .toList();
    }
}
