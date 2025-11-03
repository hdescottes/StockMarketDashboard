package com.project.reactdashboard.infrastructure.stock.persistence;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.infrastructure.stock.controllers.mapper.StockMapper;
import com.project.reactdashboard.infrastructure.stock.entities.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomStockModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockRepositoryAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @Mock
    private StockMapper mapper;

    @Mock
    private StockJpaRepository stockJpaRepository;

    @InjectMocks
    private StockRepositoryAdapter repository;

    @Test
    void upsert_should_succeed() {
        // Given
        StockModel stock = randomStockModel();

        when(entityManager.createNativeQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyInt(), any())).thenReturn(query);

        // When
        repository.upsert(stock);

        // Then
        verify(entityManager).createNativeQuery(anyString());
        verify(query).setParameter(1, stock.getDate());
        verify(query).setParameter(2, stock.getSymbol());
        verify(query).setParameter(3, stock.getOpen());
        verify(query).setParameter(4, stock.getHigh());
        verify(query).setParameter(5, stock.getLow());
        verify(query).setParameter(6, stock.getClose());
        verify(query).setParameter(7, stock.getVolume());
        verify(query).executeUpdate();
    }

    @Test
    void findAll_should_return_mapped_stockModels() {
        // Given
        List<Stock> entities = List.of(randomStock(), randomStock());
        when(stockJpaRepository.findAll()).thenReturn(entities);
        when(mapper.toModel(any(Stock.class))).thenReturn(randomStockModel());

        // When
        List<StockModel> result = repository.findAll();

        // Then
        verify(stockJpaRepository).findAll();
        verify(mapper).toModel(entities.get(0));
        verify(mapper).toModel(entities.get(1));
        assertEquals(entities.size(), result.size());
    }

    @Test
    void findBySymbol_should_return_mapped_stockModels() {
        // Given
        String symbol = "AAPL";
        OffsetDateTime date = OffsetDateTime.now();
        List<Stock> entities = List.of(randomStock());
        when(stockJpaRepository.findBySymbol(symbol, date)).thenReturn(entities);
        when(mapper.toModel(any(Stock.class))).thenReturn(randomStockModel());

        // When
        List<StockModel> result = repository.findBySymbol(symbol, date);

        // Then
        verify(stockJpaRepository).findBySymbol(symbol, date);
        verify(mapper).toModel(entities.getFirst());
        assertEquals(entities.size(), result.size());
    }

    @Test
    void findAllLatest_should_return_mapped_stockModels() {
        // Given
        List<Stock> entities = List.of(new Stock());
        when(stockJpaRepository.findAllLatest()).thenReturn(entities);
        when(mapper.toModel(any(Stock.class))).thenReturn(randomStockModel());

        // When
        List<StockModel> result = repository.findAllLatest();

        // Then
        verify(stockJpaRepository).findAllLatest();
        verify(mapper).toModel(entities.getFirst());
        assertEquals(entities.size(), result.size());
    }

    @Test
    void findLastWorkingDayBySymbol_should_return_mapped_stockModel() {
        // Given
        String symbol = "AAPL";
        OffsetDateTime date = OffsetDateTime.now();
        Stock entity = randomStock();
        when(stockJpaRepository.findLastWorkingDayBySymbol(symbol, date)).thenReturn(entity);
        when(mapper.toModel(entity)).thenReturn(randomStockModel());

        // When
        StockModel result = repository.findLastWorkingDayBySymbol(symbol, date);

        // Then
        verify(stockJpaRepository).findLastWorkingDayBySymbol(symbol, date);
        verify(mapper).toModel(entity);
        assertNotNull(result);
    }
}
