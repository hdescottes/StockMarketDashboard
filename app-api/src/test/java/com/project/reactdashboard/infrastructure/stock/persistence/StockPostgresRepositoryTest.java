package com.project.reactdashboard.infrastructure.stock.persistence;

import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureEmbeddedDatabase(provider = ZONKY, refresh = BEFORE_EACH_TEST_METHOD)
public class StockPostgresRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockPostgresRepository repository;

    @Test
    void should_upsert_stock() {
        Stock stock = randomStock();
        stock.setSymbol("ML.XPAR");

        repository.upsert(stock);
        List<Stock> dbStocks = stockRepository.findAll();

        assertEquals(1, dbStocks.size());
        assertEquals(stock.getSymbol(), dbStocks.get(0).getSymbol());
        assertEquals(stock.getHigh(), dbStocks.get(0).getHigh());
    }
}
