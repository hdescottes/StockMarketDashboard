package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StockMarketServiceTest {

    @Mock
    private StockMarketRepository repository;

    @InjectMocks
    private StockMarketService service;

    @Test
    void should_create_model() {
        Model model = new Model.ModelBuilder().build();

        service.create(model);

        verify(repository).save(model);
    }

    @Test
    void should_find_all_model() {
        service.findAll();

        verify(repository).findAll();
    }
}
