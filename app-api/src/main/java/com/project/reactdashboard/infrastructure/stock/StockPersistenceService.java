package com.project.reactdashboard.infrastructure.stock;

import com.project.reactdashboard.domain.stock.StockSpi;
import com.project.reactdashboard.domain.stock.model.Stock;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StockPersistenceService implements StockSpi {

    private final StockPostgresRepository stockRepository;

    private final StockRepository repository;

    public StockPersistenceService(StockRepository repository, StockPostgresRepository stockRepository) {
        this.repository = repository;
        this.stockRepository = stockRepository;
    }

    public void createAll(List<Stock> stocks) {
        stocks.forEach(stockRepository::upsert);
    }

    public List<Stock> findBySymbol(String symbol, OffsetDateTime date) {
        return repository.findBySymbol(symbol, date);
    }

    public List<Stock> findAllLatest() {
        return repository.findAllLatest();
    }

    public Stock findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay) {
        return repository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
    }
}
