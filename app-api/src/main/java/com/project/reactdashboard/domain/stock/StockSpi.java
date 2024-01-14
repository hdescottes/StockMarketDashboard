package com.project.reactdashboard.domain.stock;

import com.project.reactdashboard.domain.stock.model.StockDomain;

import java.time.OffsetDateTime;
import java.util.List;

public interface StockSpi {

    void createAll(List<StockDomain> stockDomains);

    List<StockDomain> findBySymbol(String symbol, OffsetDateTime date);

    List<StockDomain> findAllLatest();

    StockDomain findLastWorkingDayBySymbol(String symbol, OffsetDateTime lastWorkingDay);
}
