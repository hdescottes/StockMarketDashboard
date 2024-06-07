package com.project.reactdashboard.infrastructure.stock.persistence.mapper;

import com.project.reactdashboard.application.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockMapper {

    private final SymbolValuesMapper symbolValuesMapper;

    public StockMapper(SymbolValuesMapper symbolValuesMapper) {
        this.symbolValuesMapper = symbolValuesMapper;
    }

    public StockApplication toApplication(Stock app) {
        return new StockApplication.StockDomainBuilder()
                .withId(app.getId())
                .withSymbol(app.getSymbol())
                .withSymbolValues(symbolValuesMapper.toApplication(app.getSymbolValues()))
                .withDate(app.getDate())
                .withVolume(app.getVolume())
                .withOpen(app.getOpen())
                .withClose(app.getClose())
                .withHigh(app.getHigh())
                .withLow(app.getLow())
                .build();
    }

    public Stock toEntity(StockApplication app) {
        return new Stock.StockInfraBuilder()
                .withId(app.getId())
                .withSymbol(app.getSymbol())
                .withDate(app.getDate())
                .withVolume(app.getVolume())
                .withOpen(app.getOpen())
                .withClose(app.getClose())
                .withHigh(app.getHigh())
                .withLow(app.getLow())
                .build();
    }

    public List<Stock> toList(List<StockApplication> stockApplications) {
        return stockApplications.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<StockApplication> toListDomain(List<Stock> stocks) {
        return stocks.stream()
                .map(this::toApplication)
                .toList();
    }
}
