package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.stock.repositories.StockPostgresRepository;
import com.project.reactdashboard.stock.repositories.StockRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository repository;

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
        when(repository.findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class))).thenReturn(dbStock);

        Stock result = service.findLastWorkingDayBySymbol(randomString());

        verify(repository).findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class));
        assertEquals(dbStock, result);
    }

    @Test
    void should_find_all_latest_stock() {
        service.findAllLatest();

        verify(repository).findAllLatest();
    }
}
