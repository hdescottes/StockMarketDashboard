package com.project.reactdashboard.application.stock;

import com.project.reactdashboard.application.stock.model.StockApplication;

import java.util.List;

public interface StockApi {

    void createAll(List<StockApplication> stockApplications);

    List<StockApplication> findBySymbol(String symbol);

    List<StockApplication> findAllLatest();

    StockApplication findLastWorkingDayBySymbol(String symbol);
}
