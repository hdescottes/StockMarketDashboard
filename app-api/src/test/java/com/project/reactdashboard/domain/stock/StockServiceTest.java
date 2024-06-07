package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.application.stock.StockSpi;
import com.project.reactdashboard.application.stock.model.StockApplication;
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
        List<StockApplication> stockApplications = randomList(i -> randomStockDomain());

        service.createAll(stockApplications);

        verify(stockSpi).createAll(stockApplications);
    }

    @Test
    void should_find_a_stock() {
        StockApplication dbStockApplication = randomStockDomain();
        when(stockSpi.findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class))).thenReturn(dbStockApplication);

        StockApplication result = service.findLastWorkingDayBySymbol(randomString());

        verify(stockSpi).findLastWorkingDayBySymbol(anyString(), any(OffsetDateTime.class));
        assertEquals(dbStockApplication, result);
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
