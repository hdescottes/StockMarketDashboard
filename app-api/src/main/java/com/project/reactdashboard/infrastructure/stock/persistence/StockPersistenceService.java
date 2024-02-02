package com.project.reactdashboard.infrastructure.stock.persistence;

import com.project.reactdashboard.appplication.stock.StockSpi;
import com.project.reactdashboard.appplication.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class StockPersistenceService implements StockSpi {

    private final StockPostgresRepository stockRepository;

    private final StockRepository repository;

    private final StockMapper mapper;

    public StockPersistenceService(StockRepository repository, StockPostgresRepository stockRepository,
                                   StockMapper mapper) {
        this.repository = repository;
        this.stockRepository = stockRepository;
        this.mapper = mapper;
    }

    public void createAll(List<StockApplication> stockApplications) {
        List<Stock> stocks = mapper.toList(stockApplications);
        stocks.forEach(stockRepository::upsert);
    }

    public List<StockApplication> findBySymbol(String symbol, OffsetDateTime date) {
        List<Stock> stocks = repository.findBySymbol(symbol, date);
        return mapper.toListDomain(stocks);
    }

    public List<StockApplication> findAllLatest() {
        List<Stock> stocks = repository.findAllLatest();
        return mapper.toListDomain(stocks);
    }

    public StockApplication findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay) {
        Stock stock = repository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
        StockApplication stockApplication = new StockApplication.StockDomainBuilder().build();
        if (stock != null) {
            stockApplication = mapper.toApplication(stock);
        }
        return stockApplication;
    }
}
