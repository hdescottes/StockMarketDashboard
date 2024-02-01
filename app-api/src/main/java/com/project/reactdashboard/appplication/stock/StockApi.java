package com.project.reactdashboard.appplication.stock;

import com.project.reactdashboard.appplication.stock.model.StockApplication;

import java.util.List;

public interface StockApi {

    void createAll(List<StockApplication> stockApplications);

    List<StockApplication> findBySymbol(String symbol);

    List<StockApplication> findAllLatest();

    StockApplication findLastWorkingDayBySymbol(String symbol);
}
