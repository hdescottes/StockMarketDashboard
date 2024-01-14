package com.project.reactdashboard.infrastructure.stock;

import com.project.reactdashboard.infrastructure.stock.model.Stock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPostgresRepository {

    void upsert(@Param("stock") Stock stock);

}
