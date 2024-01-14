package com.project.reactdashboard.infrastructure.stock;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.infrastructure.stock.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDomain;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockPersistenceServiceTest {

    @Mock
    private StockMapper mapper;

    @Mock
    private StockRepository repository;

    @Mock
    private StockPostgresRepository postgresRepository;

    @InjectMocks
    private StockPersistenceService service;

    @Test
    void should_create_list_of_stock() {
        List<StockDomain> stockDomains = randomList(i -> randomStockDomain());
        List<Stock> stocks = randomList(i -> randomStock());
        when(mapper.toList(stockDomains)).thenReturn(stocks);

        service.createAll(stockDomains);

        verify(postgresRepository, times(stockDomains.size())).upsert(any(Stock.class));
    }

    @Test
    void should_find_a_stock() {
        StockDomain stockDomain = randomStockDomain();
        Stock dbStock = randomStock();
        String symbol = randomString();
        OffsetDateTime date = OffsetDateTime.now();
        when(mapper.toDomain(dbStock)).thenReturn(stockDomain);
        when(repository.findLastWorkingDayBySymbol(symbol, date)).thenReturn(dbStock);

        StockDomain result = service.findLastWorkingDayBySymbol(symbol, date);

        verify(repository).findLastWorkingDayBySymbol(symbol, date);
        assertEquals(stockDomain, result);
    }

    @Test
    void should_find_all_stock_by_symbol() {
        String symbol = randomString();
        OffsetDateTime date = OffsetDateTime.now();

        service.findBySymbol(symbol, date);

        verify(repository).findBySymbol(symbol, date);
    }

    @Test
    void should_find_all_latest_stock() {
        service.findAllLatest();

        verify(repository).findAllLatest();
    }
}
