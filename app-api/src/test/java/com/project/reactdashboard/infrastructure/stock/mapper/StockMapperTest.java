package com.project.reactdashboard.infrastructure.stock.mapper;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.infrastructure.stock.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDomain;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockMapperTest {

    private StockMapper mapper;

    @BeforeEach
    void setup() {
        SymbolValuesMapper symbolValuesMapper = new SymbolValuesMapper();
        mapper = new StockMapper(symbolValuesMapper);
    }

    @Test
    void toEntity() {
        Stock infra = randomStock();

        StockDomain stockDomain = mapper.toDomain(infra);

        assertEquals(infra.getDate().toString(), stockDomain.getDate().toString());
        assertEquals(infra.getSymbol(), stockDomain.getSymbol());
        assertEquals(infra.getVolume(), stockDomain.getVolume());
        assertEquals(infra.getHigh(), stockDomain.getHigh());
        assertEquals(infra.getLow(), stockDomain.getLow());
        assertEquals(infra.getOpen(), stockDomain.getOpen());
        assertEquals(infra.getClose(), stockDomain.getClose());
    }

    @Test
    void toInfra() {
        StockDomain stockDomain = randomStockDomain();

        Stock infra = mapper.toEntity(stockDomain);

        assertEquals(infra.getId(), stockDomain.getId());
        assertEquals(infra.getDate(), stockDomain.getDate());
        assertEquals(infra.getSymbol(), stockDomain.getSymbol());
        assertEquals(infra.getVolume(), stockDomain.getVolume());
        assertEquals(infra.getHigh(), stockDomain.getHigh());
        assertEquals(infra.getLow(), stockDomain.getLow());
        assertEquals(infra.getOpen(), stockDomain.getOpen());
        assertEquals(infra.getClose(), stockDomain.getClose());
    }

    @Test
    void toListInfra() {
        List<StockDomain> stockDomains = randomList(i -> randomStockDomain());

        List<Stock> infras = mapper.toList(stockDomains);

        assertEquals(stockDomains.size(), infras.size());
        assertEquals(stockDomains.get(0).getId(), infras.get(0).getId());
    }

    @Test
    void toList() {
        List<Stock> infras = randomList(i -> randomStock());

        List<StockDomain> stockDomains = mapper.toListDomain(infras);

        assertEquals(infras.size(), stockDomains.size());
        assertEquals(infras.get(0).getId(), stockDomains.get(0).getId());
    }
}
