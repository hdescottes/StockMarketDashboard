package com.project.reactdashboard.domain.stock.mapper;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.app.stock.StockDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDomain;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDto;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockDomainMapperTest {

    private StockDomainMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new StockDomainMapper();
    }

    @Test
    void toEntity() {
        StockDto dto = randomStockDto();

        StockDomain stockDomain = mapper.toDomain(dto);

        assertEquals("2019-02-01T00:00Z", stockDomain.getDate().toString());
        assertEquals(dto.getSymbol(), stockDomain.getSymbol());
        assertEquals(dto.getVolume(), stockDomain.getVolume());
        assertEquals(dto.getHigh(), stockDomain.getHigh());
        assertEquals(dto.getLow(), stockDomain.getLow());
        assertEquals(dto.getOpen(), stockDomain.getOpen());
        assertEquals(dto.getClose(), stockDomain.getClose());
    }

    @Test
    void toDto() {
        StockDomain stockDomain = randomStockDomain();

        StockDto dto = mapper.toDto(stockDomain);

        assertEquals(dto.getId(), stockDomain.getId());
        assertEquals(dto.getDate(), stockDomain.getDate().toString());
        assertEquals(dto.getSymbol(), stockDomain.getSymbol());
        assertEquals(dto.getName(), stockDomain.getSymbolValues().getName());
        assertEquals(dto.getVolume(), stockDomain.getVolume());
        assertEquals(dto.getHigh(), stockDomain.getHigh());
        assertEquals(dto.getLow(), stockDomain.getLow());
        assertEquals(dto.getOpen(), stockDomain.getOpen());
        assertEquals(dto.getClose(), stockDomain.getClose());
    }

    @Test
    void toListDto() {
        List<StockDomain> stockDomains = randomList(i -> randomStockDomain());

        List<StockDto> dtos = mapper.toListDto(stockDomains);

        assertEquals(stockDomains.size(), dtos.size());
        assertEquals(stockDomains.get(0).getId(), dtos.get(0).getId());
    }

    @Test
    void toList() {
        List<StockDto> dtos = randomList(i -> randomStockDto());

        List<StockDomain> stockDomains = mapper.toListDomain(dtos);

        assertEquals(dtos.size(), stockDomains.size());
        assertEquals(dtos.get(0).getId(), stockDomains.get(0).getId());
    }
}
