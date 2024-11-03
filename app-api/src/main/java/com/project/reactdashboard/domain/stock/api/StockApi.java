package com.project.reactdashboard.domain.stock.api;

import com.project.reactdashboard.domain.stock.entities.Stock;

import java.util.List;

public interface StockApi {

    void createAll(List<Stock> stockApplications);

    List<Stock> findBySymbol(String symbol);

    List<Stock> findAllLatest();

    Stock findLastWorkingDayBySymbol(String symbol);
}
