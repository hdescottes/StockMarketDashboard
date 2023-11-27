package com.project.reactdashboard;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.entities.StockDto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class ObjectRandomizer {

    public static String randomString() {
        return randomAlphabetic(10);
    }

    public static double randomDouble() {
        return nextDouble();
    }

    public static <T> List<T> randomList(final Function<Integer, T> mapper) {
        return IntStream.range(0, 5)
                .boxed()
                .map(mapper)
                .toList();
    }

    public static Stock randomStock() {
        return new Stock.StockBuilder()
                .withId(randomString())
                .withSymbol(randomString())
                .withDate(randomString())
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }

    public static StockDto randomStockDto() {
        return new StockDto.StockDtoBuilder()
                .withId(randomString())
                .withSymbol(randomString())
                .withDate(randomString())
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }
}
