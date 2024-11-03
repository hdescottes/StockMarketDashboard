package com.project.reactdashboard.infrastructure.stock.controllers.mapper;

import com.project.reactdashboard.domain.stock.entities.Stock;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
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

        Stock stockApplication = mapper.toEntity(dto);

        assertEquals("2019-02-01T00:00Z", stockApplication.getDate().toString());
        assertEquals(dto.getSymbol(), stockApplication.getSymbol());
        assertEquals(dto.getVolume(), stockApplication.getVolume());
        assertEquals(dto.getHigh(), stockApplication.getHigh());
        assertEquals(dto.getLow(), stockApplication.getLow());
        assertEquals(dto.getOpen(), stockApplication.getOpen());
        assertEquals(dto.getClose(), stockApplication.getClose());
    }

    @Test
    void toDto() {
        Stock stockApplication = randomStock();

        StockDto dto = mapper.toDto(stockApplication);

        assertEquals(dto.getId(), stockApplication.getId());
        assertEquals(dto.getDate(), stockApplication.getDate().toString());
        assertEquals(dto.getSymbol(), stockApplication.getSymbol());
        assertEquals(dto.getName(), stockApplication.getSymbolValues().getName());
        assertEquals(dto.getVolume(), stockApplication.getVolume());
        assertEquals(dto.getHigh(), stockApplication.getHigh());
        assertEquals(dto.getLow(), stockApplication.getLow());
        assertEquals(dto.getOpen(), stockApplication.getOpen());
        assertEquals(dto.getClose(), stockApplication.getClose());
    }

    @Test
    void toListDto() {
        List<Stock> stockApplications = randomList(i -> randomStock());

        List<StockDto> dtos = mapper.toListDto(stockApplications);

        assertEquals(stockApplications.size(), dtos.size());
        assertEquals(stockApplications.get(0).getId(), dtos.get(0).getId());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<Stock> stockApplications = mapper.toListDomain(dtos);

        assertEquals(dtos.size(), stockApplications.size());
        assertEquals(dtos.get(0).getId(), stockApplications.get(0).getId());
    }
}
