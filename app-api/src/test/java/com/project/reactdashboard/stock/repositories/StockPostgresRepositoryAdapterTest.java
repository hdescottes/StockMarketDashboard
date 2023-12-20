package com.project.reactdashboard.stock.repositories;

import com.project.reactdashboard.entities.Stock;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockPostgresRepositoryAdapterTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private StockPostgresRepositoryAdapter repository;

    @Test
    public void testUpsert() {
        // Given
        Stock stock = randomStock();

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
}
