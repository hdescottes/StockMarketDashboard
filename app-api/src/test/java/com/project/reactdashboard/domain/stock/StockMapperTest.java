package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.StockMapper;
import com.project.reactdashboard.domain.stock.model.Stock;
import com.project.reactdashboard.app.stock.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
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

        assertEquals("2019-02-01T00:00Z", stock.getDate().toString());
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
        assertEquals(dto.getDate(), stock.getDate().toString());
        assertEquals(dto.getSymbol(), stock.getSymbol());
        assertEquals(dto.getName(), stock.getSymbolValues().getName());
        assertEquals(dto.getVolume(), stock.getVolume());
        assertEquals(dto.getHigh(), stock.getHigh());
        assertEquals(dto.getLow(), stock.getLow());
        assertEquals(dto.getOpen(), stock.getOpen());
        assertEquals(dto.getClose(), stock.getClose());
    }

    @Test
    void toListDto() {
        List<Stock> stocks = randomList(i -> randomStock());

        List<StockDto> dtos = mapper.toListDto(stocks);

        assertEquals(stocks.size(), dtos.size());
        assertEquals(stocks.get(0).getId(), dtos.get(0).getId());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<Stock> stocks = mapper.toList(dtos);

        assertEquals(dtos.size(), stocks.size());
        assertEquals(dtos.get(0).getId(), stocks.get(0).getId());
    }
}
