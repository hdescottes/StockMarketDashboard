package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.entities.Stock;
import com.project.reactdashboard.domain.stock.spi.StockJpaRepository;
import com.project.reactdashboard.domain.stock.spi.StockPostgresRepository;
import com.project.reactdashboard.infrastructure.stock.controllers.mapper.StockMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockMapper mapper;

    @Mock
    private StockJpaRepository jpaRepository;

    @Mock
    private StockPostgresRepository postgresRepository;

    @InjectMocks
    private StockService service;

    @Test
    void should_create_list_of_stock() {
        List<Stock> stocks = randomList(i -> randomStock());

        service.createAll(stocks);

        verify(postgresRepository, times(stocks.size())).upsert(any(Stock.class));
    }

    @Test
    void should_find_a_stock() {
        Stock dbStock = randomStock();
        String symbol = randomString();
        OffsetDateTime date = Date.lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        when(jpaRepository.findLastWorkingDayBySymbol(symbol, date)).thenReturn(dbStock);

        service.findLastWorkingDayBySymbol(symbol);

        verify(jpaRepository).findLastWorkingDayBySymbol(symbol, date);
    }

    @Test
    void should_find_all_stock_by_symbol() {
        String symbol = randomString();

        service.findBySymbol(symbol);

        verify(jpaRepository).findBySymbol(eq(symbol), any(OffsetDateTime.class));
    }

    @Test
    void should_find_all_latest_stock() {
        service.findAllLatest();

        verify(jpaRepository).findAllLatest();
    }
}
