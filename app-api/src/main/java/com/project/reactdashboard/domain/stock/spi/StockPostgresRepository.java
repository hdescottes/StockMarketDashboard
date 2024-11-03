package com.project.reactdashboard.domain.stock.spi;

import com.project.reactdashboard.domain.stock.entities.Stock;

public interface StockPostgresRepository {

    void upsert(Stock stock);

}
