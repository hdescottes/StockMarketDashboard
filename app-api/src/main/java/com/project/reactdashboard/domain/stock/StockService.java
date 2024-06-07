package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.application.stock.StockApi;
import com.project.reactdashboard.application.stock.StockSpi;
import com.project.reactdashboard.application.stock.model.StockApplication;
import com.project.reactdashboard.configuration.UseCase;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.lastWorkingDay;

@UseCase
public class StockService implements StockApi {

    private final StockSpi stockSpi;

    public StockService(StockSpi stockSpi) {
        this.stockSpi = stockSpi;
    }

    public void createAll(List<StockApplication> stockApplications) {
        stockSpi.createAll(stockApplications);
    }

    public List<StockApplication> findBySymbol(String symbol) {
        OffsetDateTime date = OffsetDateTime.now().minusMonths(1);
        return stockSpi.findBySymbol(symbol, date);
    }

    public List<StockApplication> findAllLatest() {
        return stockSpi.findAllLatest();
    }

    public StockApplication findLastWorkingDayBySymbol(String symbol) {
        OffsetDateTime lastWorkingDay = lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        return stockSpi.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
    }
}
