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
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomStockModelWithSymbol;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.BEFORE_EACH_TEST_METHOD;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureEmbeddedDatabase(provider = ZONKY, refresh = BEFORE_EACH_TEST_METHOD)
public class StockPostgresRepositoryTest {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired
    private StockRepository repository;

    @Test
    void should_upsert_stock() {
        StockModel stock = randomStockModelWithSymbol("ML.XPAR");

        repository.upsert(stock);
        List<StockModel> dbStocks = repository.findAll();

        assertEquals(1, dbStocks.size());
        assertEquals(stock.symbol(), dbStocks.getFirst().symbol());
        assertEquals(stock.high(), dbStocks.getFirst().high());
    }
}
