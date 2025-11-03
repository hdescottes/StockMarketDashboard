package com.project.reactdashboard.domain.stock.api;

import com.project.reactdashboard.domain.stock.model.StockModel;

import java.util.List;

public interface StockApi {

    void createAll(List<StockModel> stockApplications);

    List<StockModel> findBySymbol(String symbol);

    List<StockModel> findAllLatest();

    StockModel findLastWorkingDayBySymbol(String symbol);
}
