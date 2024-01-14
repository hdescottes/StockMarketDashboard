package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.Stock;

import java.util.List;

public interface StockApi {

    void createAll(List<Stock> stocks);

    List<Stock> findBySymbol(String symbol);

    List<Stock> findAllLatest();

    Stock findLastWorkingDayBySymbol(String symbol);
}
