package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomList;
import static com.project.reactdashboard.ObjectRandomizer.randomStockDomain;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockSpi stockSpi;

    @InjectMocks
    private StockService service;

    @Test
    void should_create_list_of_stock() {
        List<StockDomain> stockDomains = randomList(i -> randomStockDomain());

        service.createAll(stockDomains);

        verify(stockSpi).createAll(stockDomains);
    }

    @Test
    void should_find_a_stock() {
        StockDomain dbStockDomain = randomStockDomain();
        when(stockSpi.findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class))).thenReturn(dbStockDomain);

        StockDomain result = service.findLastWorkingDayBySymbol(randomString());

        verify(stockSpi).findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class));
        assertEquals(dbStockDomain, result);
    }

    @Test
    void should_find_all_stock_by_symbol() {
        String symbol = randomString();

        service.findBySymbol(symbol);

        verify(stockSpi).findBySymbol(eq(symbol), any(OffsetDateTime.class));
    }

    @Test
    void should_find_all_latest_stock() {
        service.findAllLatest();

        verify(stockSpi).findAllLatest();
    }
}
