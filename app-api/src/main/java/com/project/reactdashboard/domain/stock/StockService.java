package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.UseCase;
import com.project.reactdashboard.domain.stock.api.StockApi;
import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.domain.stock.spi.StockRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.lastWorkingDay;

@UseCase
public class StockService implements StockApi {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void createAll(List<StockModel> stocks) {
        stocks.forEach(stockRepository::upsert);
    }

    public List<StockModel> findBySymbol(String symbol) {
        OffsetDateTime date = OffsetDateTime.now().minusMonths(1);
        return stockRepository.findBySymbol(symbol, date);
    }

    public List<StockModel> findAllLatest() {
        return stockRepository.findAllLatest();
    }

    public StockModel findLastWorkingDayBySymbol(String symbol) {
        OffsetDateTime lastWorkingDay = lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        StockModel stock = stockRepository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
        StockModel stockCheck = new StockModel.StockModelBuilder().build();
        if (stock != null) {
            stockCheck = stock;
        }
        return stockCheck;
    }
}
