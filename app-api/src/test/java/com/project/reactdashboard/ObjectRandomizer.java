package com.project.reactdashboard;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.domain.stock.model.SymbolValuesModel;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import com.project.reactdashboard.infrastructure.stock.entities.Stock;
import com.project.reactdashboard.infrastructure.stock.entities.SymbolValues;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ObjectRandomizer {

    private static final Random random = new Random();
    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String randomString() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(ALPHA.charAt(random.nextInt(ALPHA.length())));
        }
        return sb.toString();
    }

    public static double randomDouble() {
        return random.nextDouble();
    }

    public static Long randomLong() {
        return random.nextLong();
    }

    public static <T> List<T> randomList(final Function<Integer, T> mapper) {
        return IntStream.range(0, 5)
                .boxed()
                .map(mapper)
                .toList();
    }

    public static StockModel randomStockModel() {
        return new StockModel.StockModelBuilder()
                .withId(randomLong())
                .withSymbol(randomString())
                .withSymbolValues(randomSymbolValuesModel())
                .withDate(OffsetDateTime.now())
                .withVolume(randomDouble())
                .withOpen(randomDouble())
                .withClose(randomDouble())
                .withHigh(randomDouble())
                .withLow(randomDouble())
                .build();
    }

    public static Stock randomStock() {
        return new Stock.StockBuilder()
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

    public static SymbolValues randomSymbolValues() {
        return new SymbolValues.SymbolValuesBuilder()
                .withName(randomString())
                .withSymbol(randomString())
                .build();
    }

    public static SymbolValuesModel randomSymbolValuesModel() {
        return new SymbolValuesModel.SymbolValuesModelBuilder()
                .withName(randomString())
                .withSymbol(randomString())
                .build();
    }
}
