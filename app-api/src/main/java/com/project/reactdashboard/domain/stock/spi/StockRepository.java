package com.project.reactdashboard.domain.stock.spi;

import com.project.reactdashboard.domain.stock.model.StockModel;

import java.time.OffsetDateTime;
import java.util.List;

public interface StockRepository {

    void upsert(StockModel stock);

    List<StockModel> findAll();

    List<StockModel> findBySymbol(String symbol, OffsetDateTime date);

    List<StockModel> findAllLatest();

    StockModel findLastWorkingDayBySymbol(String symbol, OffsetDateTime date);

}
