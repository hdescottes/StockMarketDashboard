package com.project.reactdashboard.infrastructure.stock.persistence;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.domain.stock.spi.StockRepository;
import com.project.reactdashboard.infrastructure.stock.controllers.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.entities.Stock;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class StockRepositoryAdapter implements StockRepository {

    private final StockMapper mapper;

    private final StockJpaRepository stockJpaRepository;

    private final EntityManager entityManager;

    public StockRepositoryAdapter(StockMapper mapper, StockJpaRepository stockJpaRepository, EntityManager entityManager) {
        this.mapper = mapper;
        this.stockJpaRepository = stockJpaRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public void upsert(StockModel stock) {
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

    @Override
    public List<StockModel> findAll() {
        return stockJpaRepository.findAll().stream()
                .map(mapper::toModel)
                .toList();
    }

    public List<StockModel> findBySymbol(String symbol, OffsetDateTime date) {
        return stockJpaRepository.findBySymbol(symbol, date).stream()
                .map(mapper::toModel)
                .toList();
    }

    public List<StockModel> findAllLatest() {
        return stockJpaRepository.findAllLatest().stream()
                .map(mapper::toModel)
                .toList();
    }

    public StockModel findLastWorkingDayBySymbol(String symbol, OffsetDateTime date) {
        Stock stock = stockJpaRepository.findLastWorkingDayBySymbol(symbol, date);
        if (stock != null) {
            return mapper.toModel(stock);
        }
        return null;
    }
}
