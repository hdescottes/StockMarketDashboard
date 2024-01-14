package com.project.reactdashboard.infrastructure.stock;

import com.project.reactdashboard.domain.stock.StockSpi;
import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.infrastructure.stock.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.model.Stock;
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

    public void createAll(List<StockDomain> stockDomains) {
        List<Stock> stocks = mapper.toList(stockDomains);
        stocks.forEach(stockRepository::upsert);
    }

    public List<StockDomain> findBySymbol(String symbol, OffsetDateTime date) {
        List<Stock> stocks = repository.findBySymbol(symbol, date);
        return mapper.toListDomain(stocks);
    }

    public List<StockDomain> findAllLatest() {
        List<Stock> stocks = repository.findAllLatest();
        return mapper.toListDomain(stocks);
    }

    public StockDomain findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay) {
        Stock stock = repository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
        StockDomain stockDomain = new StockDomain.StockDomainBuilder().build();
        if (stock != null) {
            stockDomain = mapper.toDomain(stock);
        }
        return stockDomain;
    }
}
