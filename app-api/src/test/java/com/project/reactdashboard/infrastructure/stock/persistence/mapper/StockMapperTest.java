package com.project.reactdashboard.infrastructure.stock.persistence.mapper;

import com.project.reactdashboard.appplication.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import com.project.reactdashboard.infrastructure.stock.persistence.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.persistence.mapper.SymbolValuesMapper;
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

        StockApplication stockApplication = mapper.toApplication(infra);

        assertEquals(infra.getDate().toString(), stockApplication.getDate().toString());
        assertEquals(infra.getSymbol(), stockApplication.getSymbol());
        assertEquals(infra.getVolume(), stockApplication.getVolume());
        assertEquals(infra.getHigh(), stockApplication.getHigh());
        assertEquals(infra.getLow(), stockApplication.getLow());
        assertEquals(infra.getOpen(), stockApplication.getOpen());
        assertEquals(infra.getClose(), stockApplication.getClose());
    }

    @Test
    void toInfra() {
        StockApplication stockApplication = randomStockDomain();

        Stock infra = mapper.toEntity(stockApplication);

        assertEquals(infra.getId(), stockApplication.getId());
        assertEquals(infra.getDate(), stockApplication.getDate());
        assertEquals(infra.getSymbol(), stockApplication.getSymbol());
        assertEquals(infra.getVolume(), stockApplication.getVolume());
        assertEquals(infra.getHigh(), stockApplication.getHigh());
        assertEquals(infra.getLow(), stockApplication.getLow());
        assertEquals(infra.getOpen(), stockApplication.getOpen());
        assertEquals(infra.getClose(), stockApplication.getClose());
    }

    @Test
    void toListInfra() {
        List<StockApplication> stockApplications = randomList(i -> randomStockDomain());

        List<Stock> infras = mapper.toList(stockApplications);

        assertEquals(stockApplications.size(), infras.size());
        assertEquals(stockApplications.get(0).getId(), infras.get(0).getId());
    }

    @Test
    void toList() {
        List<Stock> infras = randomList(i -> randomStock());

        List<StockApplication> stockApplications = mapper.toListDomain(infras);

        assertEquals(infras.size(), stockApplications.size());
        assertEquals(infras.get(0).getId(), stockApplications.get(0).getId());
    }
}
