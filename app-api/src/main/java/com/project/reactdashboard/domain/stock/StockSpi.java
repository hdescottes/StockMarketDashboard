package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.Stock;

import java.time.OffsetDateTime;
import java.util.List;

public interface StockSpi {

    void createAll(List<Stock> stocks);

    List<Stock> findBySymbol(String symbol, OffsetDateTime date);

    List<Stock> findAllLatest();

    Stock findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay);
}
