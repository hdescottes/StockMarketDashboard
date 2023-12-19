package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    @Query("SELECT s " +
            "FROM Stock s " +
            "WHERE s.date IN (SELECT MAX(s2.date) FROM Stock s2 WHERE s2.symbol = s.symbol)")
    List<Stock> findAllLatest();

    @Query("SELECT s.symbol, s.date, s.open, s.close, s.volume, s.high, s.low" +
            "FROM Stock s " +
            "WHERE s.symbol = :symbol AND s.date = :date")
    Stock findLastWorkingDayBySymbol(@Param("symbol") String symbol, @Param("date") OffsetDateTime date);

}
