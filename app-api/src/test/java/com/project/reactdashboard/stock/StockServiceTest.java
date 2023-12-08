package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.project.reactdashboard.ObjectRandomizer.randomStock;
import static com.project.reactdashboard.ObjectRandomizer.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository repository;

    @InjectMocks
    private StockService service;

    @Test
    void should_create_stock() {
        Stock model = randomStock();

        service.create(model);

        verify(repository).save(model);
    }

    @Test
    void should_find_a_stock() {
        String id = randomString();
        Stock dbStock = randomStock();
        when(repository.findById(id)).thenReturn(Optional.ofNullable(dbStock));

        Stock result = service.findById(id);

        verify(repository).findById(id);
        assertEquals(dbStock, result);
    }

    @Test
    void should_not_find_a_stock() {
        String id = randomString();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Stock result = service.findById(id);

        verify(repository).findById(id);
        assertNull(result);
    }

    @Test
    void should_find_all_stock() {
        service.findAll();

        verify(repository).findAll();
    }
}
