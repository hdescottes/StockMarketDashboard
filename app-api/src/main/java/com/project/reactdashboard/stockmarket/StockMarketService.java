package com.project.reactdashboard.stockmarket;

import com.project.reactdashboard.entities.Model;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMarketService {

    private final StockMarketRepository repository;

    public StockMarketService(StockMarketRepository repository) {
        this.repository = repository;
    }

    public void create(Model model) {
        repository.save(model);
    }

    public List<Model> findAll() {
        return (List<Model>) repository.findAll();
    }
}
