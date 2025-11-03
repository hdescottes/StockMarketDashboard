package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.domain.stock.spi.StockRepository;
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
import static com.project.reactdashboard.ObjectRandomizer.randomStockModel;
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
    private StockRepository repository;

    @InjectMocks
    private StockService service;

    @Test
    void should_create_list_of_stock() {
        List<StockModel> stocks = randomList(i -> randomStockModel());

        service.createAll(stocks);

        verify(repository, times(stocks.size())).upsert(any(StockModel.class));
    }

    @Test
    void should_find_a_stock() {
        StockModel dbStock = randomStockModel();
        String symbol = randomString();
        OffsetDateTime date = Date.lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        when(repository.findLastWorkingDayBySymbol(symbol, date)).thenReturn(dbStock);

        service.findLastWorkingDayBySymbol(symbol);

        verify(repository).findLastWorkingDayBySymbol(symbol, date);
    }

    @Test
    void should_find_all_stock_by_symbol() {
        String symbol = randomString();

        service.findBySymbol(symbol);

        verify(repository).findBySymbol(eq(symbol), any(OffsetDateTime.class));
    }

    @Test
    void should_find_all_latest_stock() {
        service.findAllLatest();

        verify(repository).findAllLatest();
    }
}
