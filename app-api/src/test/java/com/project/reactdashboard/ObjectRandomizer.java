package com.project.reactdashboard;

import com.project.reactdashboard.appplication.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import com.project.reactdashboard.appplication.stock.model.SymbolValuesApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.SymbolValues;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class ObjectRandomizer {

    public static String randomString() {
        return randomAlphabetic(10);
    }

    public static double randomDouble() {
        return nextDouble();
    }

    public static Long randomLong() {
        return nextLong();
    }

    public static <T> List<T> randomList(final Function<Integer, T> mapper) {
        return IntStream.range(0, 5)
                .boxed()
                .map(mapper)
                .toList();
    }

    public static StockApplication randomStockDomain() {
        return new StockApplication.StockDomainBuilder()
                .withId(randomLong())
                .withSymbol(randomString())
                .withSymbolValues(randomSymbolValuesDomain())
                .withDate(OffsetDateTime.now())
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }

    public static Stock randomStock() {
        return new Stock.StockInfraBuilder()
                .withId(randomLong())
                .withSymbol(randomString())
                .withSymbolValues(randomSymbolValues())
                .withDate(OffsetDateTime.now())
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }

    public static StockDto randomStockDto() {
        return new StockDto.StockDtoBuilder()
                .withId(randomLong())
                .withSymbol(randomString())
                .withName(randomString())
                .withDate("2019-02-01T00:00:00+0000")
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }

    public static SymbolValuesApplication randomSymbolValuesDomain() {
        return new SymbolValuesApplication.SymbolValuesDomainBuilder()
                .withName(randomString())
                .withSymbol(randomString())
                .build();
    }

    public static SymbolValues randomSymbolValues() {
        return new SymbolValues.SymbolValuesBuilder()
                .withName(randomString())
                .withSymbol(randomString())
                .build();
    }
}
