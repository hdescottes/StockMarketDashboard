package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository repository;

    public StockService(StockRepository repository) {
        this.repository = repository;
    }

    public void create(Stock stock) {
        repository.save(stock);
    }

    public List<Stock> findAll() {
        return (List<Stock>) repository.findAll();
    }

    public Stock findById(String id) {
        return repository.findById(id)
                .orElse(null);
    }
}
