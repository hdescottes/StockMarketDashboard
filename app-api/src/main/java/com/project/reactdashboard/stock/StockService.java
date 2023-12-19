package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static com.project.reactdashboard.utils.Utils.lastWorkingDay;

@Service
public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public void create(Stock stock) {
        repository.save(stock);
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
