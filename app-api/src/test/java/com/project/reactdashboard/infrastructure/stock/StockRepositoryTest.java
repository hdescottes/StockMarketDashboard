package com.project.reactdashboard.infrastructure.stock;

import com.project.reactdashboard.infrastructure.stock.model.Stock;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"/init-zonky.sql"})
@AutoConfigureEmbeddedDatabase(provider = ZONKY)
public class StockRepositoryTest {

    @Autowired
    private StockRepository repository;

    @Test
    void should_find_stock_by_symbol() {
        OffsetDateTime date = OffsetDateTime.parse("2023-01-01T00:00:00" + "+00:00");

        List<Stock> stocks = repository.findBySymbol("MC.XPAR", date);

        assertEquals(3, stocks.size());
        assertTrue(stocks.stream().anyMatch(element -> "MC.XPAR".equals(element.getSymbol())));
    }

    @Test
    void should_find_latest_stocks() {
        List<Stock> stocks = repository.findAllLatest();

        assertEquals(2, stocks.size());
        assertEquals("POM.XPAR", stocks.get(0).getSymbol());
        assertTrue(stocks.get(0).getDate().toString().contains("2023-12-30"));
        assertEquals("MC.XPAR", stocks.get(1).getSymbol());
        assertTrue(stocks.get(1).getDate().toString().contains("2023-12-29"));
    }

    @Test
    void should_find_last_working_day_stock() {
        OffsetDateTime date = OffsetDateTime.parse("2023-12-28T00:00:00" + "+00:00")
                .withOffsetSameLocal(ZoneOffset.UTC);

        Stock stock = repository.findLastWorkingDayBySymbol("MC.XPAR", date);

        assertTrue(stock.getDate().toString().contains("2023-12-28"));
    }
}
