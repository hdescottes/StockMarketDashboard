package com.project.reactdashboard.integrationTest;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.domain.stock.spi.StockRepository;
import com.project.reactdashboard.infrastructure.stock.controllers.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.persistence.StockJpaRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.EMBEDDED;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Sql({"/init-zonky.sql"})
@AutoConfigureEmbeddedDatabase(provider = EMBEDDED, refresh = BEFORE_EACH_TEST_METHOD)
public class StockJpaRepositoryTest {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockRepository repository;

    @Test
    void should_find_stock_by_symbol() {
        OffsetDateTime date = OffsetDateTime.parse("2023-01-01T00:00:00" + "+00:00");

        List<StockModel> stocks = repository.findBySymbol("MC.XPAR", date);

        assertEquals(3, stocks.size());
        assertTrue(stocks.stream().anyMatch(element -> "MC.XPAR".equals(element.symbol())));
    }

    @Test
    void should_find_latest_stocks() {
        List<StockModel> stocks = repository.findAllLatest();

        assertEquals(2, stocks.size());
        assertEquals("POM.XPAR", stocks.get(0).symbol());
        assertTrue(stocks.get(0).date().toString().contains("2023-12-30"));
        assertEquals("MC.XPAR", stocks.get(1).symbol());
        assertTrue(stocks.get(1).date().toString().contains("2023-12-29"));
    }

    @Test
    void should_find_last_working_day_stock() {
        OffsetDateTime date = OffsetDateTime.parse("2023-12-28T00:00:00" + "+00:00")
                .withOffsetSameLocal(ZoneOffset.UTC);

        StockModel stock = repository.findLastWorkingDayBySymbol("MC.XPAR", date);

        assertTrue(stock.date().toString().contains("2023-12-28"));
    }
}
