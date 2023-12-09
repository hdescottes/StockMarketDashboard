package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.entities.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static com.project.reactdashboard.utils.Utils.splitStockDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMapperTest {

    private StockMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new StockMapper();
    }

    @Test
    void toEntity() {
        StockDto dto = randomStockDto();

        Stock stock = mapper.toEntity(dto);

        assertEquals(dto.getSymbol() + splitStockDate(dto.getDate()), stock.getId());
        assertEquals(dto.getDate(), stock.getDate());
        assertEquals(dto.getSymbol(), stock.getSymbol());
        assertEquals(dto.getVolume(), stock.getVolume());
        assertEquals(dto.getHigh(), stock.getHigh());
        assertEquals(dto.getLow(), stock.getLow());
        assertEquals(dto.getOpen(), stock.getOpen());
        assertEquals(dto.getClose(), stock.getClose());
    }

    @Test
    void toDto() {
        Stock stock = randomStock();

        StockDto dto = mapper.toDto(stock);

        assertEquals(dto.getId(), stock.getId());
        assertEquals(dto.getDate(), stock.getDate());
        assertEquals(dto.getSymbol(), stock.getSymbol());
        assertEquals(dto.getVolume(), stock.getVolume());
        assertEquals(dto.getHigh(), stock.getHigh());
        assertEquals(dto.getLow(), stock.getLow());
        assertEquals(dto.getOpen(), stock.getOpen());
        assertEquals(dto.getClose(), stock.getClose());
    }

    @Test
    void toListDto() {
        Stock stock = randomStock();
        List<Stock> stocks = randomList(i -> stock);

        List<StockDto> dtos = mapper.toListDto(stocks);

        assertEquals(stocks.size(), dtos.size());
    }
}
