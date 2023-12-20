package com.project.reactdashboard.stock.repositories;

import com.project.reactdashboard.entities.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StockPostgresRepositoryAdapter implements StockPostgresRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void upsert(Stock stock) {
        entityManager.createNativeQuery("INSERT INTO Stock (date, symbol, open, high, low, close, volume) " +
                        "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7) " +
                        "ON CONFLICT (symbol, date) DO UPDATE SET close = ?6, volume = ?7, high = ?4, low = ?5")
                .setParameter(1, stock.getDate())
                .setParameter(2, stock.getSymbol())
                .setParameter(3, stock.getOpen())
                .setParameter(4, stock.getHigh())
                .setParameter(5, stock.getLow())
                .setParameter(6, stock.getClose())
                .setParameter(7, stock.getVolume())
                .executeUpdate();
    }
}
