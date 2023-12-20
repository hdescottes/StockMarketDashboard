package com.project.reactdashboard.stock.repositories;

import com.project.reactdashboard.entities.Stock;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPostgresRepository {

    void upsert(@Param("stock") Stock stock);

}
