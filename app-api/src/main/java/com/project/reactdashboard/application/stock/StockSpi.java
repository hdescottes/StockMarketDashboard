package com.project.reactdashboard.application.stock;

import com.project.reactdashboard.application.stock.model.StockApplication;

import java.time.OffsetDateTime;
import java.util.List;

public interface StockSpi {

    void createAll(List<StockApplication> stockApplications);

    List<StockApplication> findBySymbol(String symbol, OffsetDateTime date);

    List<StockApplication> findAllLatest();

    StockApplication findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay);
}
