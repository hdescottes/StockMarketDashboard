package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.UseCase;
import com.project.reactdashboard.domain.stock.api.StockApi;
import com.project.reactdashboard.domain.stock.entities.Stock;
import com.project.reactdashboard.domain.stock.spi.StockPostgresRepository;
import com.project.reactdashboard.domain.stock.spi.StockJpaRepository;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.lastWorkingDay;

@UseCase
public class StockService implements StockApi {

    private final StockPostgresRepository stockRepository;

    private final StockJpaRepository repository;

    public StockService(StockJpaRepository repository, StockPostgresRepository stockRepository) {
        this.repository = repository;
        this.stockRepository = stockRepository;
    }

    public void createAll(List<Stock> stocks) {
        stocks.forEach(stockRepository::upsert);
    }

    public List<Stock> findBySymbol(String symbol) {
        OffsetDateTime date = OffsetDateTime.now().minusMonths(1);
        return repository.findBySymbol(symbol, date);
    }

    public List<Stock> findAllLatest() {
        return repository.findAllLatest();
    }

    public Stock findLastWorkingDayBySymbol(String symbol) {
        OffsetDateTime lastWorkingDay = lastWorkingDay()
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withOffsetSameLocal(ZoneOffset.UTC);
        Stock stock = repository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
        Stock stockCheck = new Stock.StockBuilder().build();
        if (stock != null) {
            stockCheck = stock;
        }
        return stockCheck;
    }
}
