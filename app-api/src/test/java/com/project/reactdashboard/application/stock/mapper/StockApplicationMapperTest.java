package com.project.reactdashboard.application.stock.mapper;

import com.project.reactdashboard.application.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDomain;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockApplicationMapperTest {

    private StockApplicationMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new StockApplicationMapper();
    }

    @Test
    void toEntity() {
        StockDto dto = randomStockDto();

        StockApplication stockApplication = mapper.toApplication(dto);

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
        StockApplication stockApplication = randomStockDomain();

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
        List<StockApplication> stockApplications = randomList(i -> randomStockDomain());

        List<StockDto> dtos = mapper.toListDto(stockApplications);

        assertEquals(stockApplications.size(), dtos.size());
        assertEquals(stockApplications.get(0).getId(), dtos.get(0).getId());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<StockApplication> stockApplications = mapper.toListApplication(dtos);

        assertEquals(dtos.size(), stockApplications.size());
        assertEquals(dtos.get(0).getId(), stockApplications.get(0).getId());
    }
}
