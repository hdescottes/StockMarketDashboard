package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    @Query("SELECT s.symbol, s.date, s.open, s.close, s.volume, s.high, s.low, sv.name FROM Stock s LEFT JOIN SymbolValues sv ON sv.symbol = s.symbol WHERE s.symbol = :symbol AND s.date = :date")
    Stock findLastWorkingDayBySymbol(@Param("symbol") String symbol, @Param("date") OffsetDateTime date);

}
