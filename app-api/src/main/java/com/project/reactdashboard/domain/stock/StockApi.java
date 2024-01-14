package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.StockDomain;

import java.util.List;

public interface StockApi {

    void createAll(List<StockDomain> stockDomains);

    List<StockDomain> findBySymbol(String symbol);

    List<StockDomain> findAllLatest();

    StockDomain findLastWorkingDayBySymbol(String symbol);
}
