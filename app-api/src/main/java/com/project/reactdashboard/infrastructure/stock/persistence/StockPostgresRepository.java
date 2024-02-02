package com.project.reactdashboard.infrastructure.stock.persistence;

import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPostgresRepository {

    void upsert(@Param("stock") Stock stock);

}
