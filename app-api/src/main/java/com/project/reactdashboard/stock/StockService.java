package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.stock.repositories.StockPostgresRepository;
import com.project.reactdashboard.stock.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.utils.Utils.lastWorkingDay;

@Service
public class StockService {

    private final StockPostgresRepository stockRepository;

    private final StockRepository repository;

    public StockService(StockRepository repository, StockPostgresRepository stockRepository) {
        this.repository = repository;
        this.stockRepository = stockRepository;
    }

    public void createAll(List<Stock> stocks) {
        stocks.forEach(stockRepository::upsert);
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
        return repository.findLastWorkingDayBySymbol(symbol, lastWorkingDay);
    }
}
