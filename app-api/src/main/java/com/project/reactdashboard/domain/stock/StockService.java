package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.Stock;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.domain.stock.utils.Utils.lastWorkingDay;

@Service
public class StockService implements StockApi {

    private final StockSpi stockSpi;

    public StockService(StockSpi stockSpi) {
        this.stockSpi = stockSpi;
    }

    public void createAll(List<Stock> stocks) {
        stockSpi.createAll(stocks);
    }

    public List<Stock> findBySymbol(String symbol) {
        OffsetDateTime date = OffsetDateTime.now().minusMonths(1);
        return stockSpi.findBySymbol(symbol, date);
    }

    public List<Stock> findAllLatest() {
        return stockSpi.findAllLatest();
    }

    public Stock findLastWorkingDayBySymbol(String symbol) {
        OffsetDateTime lastWorkingDay = lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        return stockSpi.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
    }
}
