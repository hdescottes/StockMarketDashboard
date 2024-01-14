package com.project.reactdashboard.infrastructure.stock.mapper;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.infrastructure.stock.model.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockMapper {

    private final SymbolValuesMapper symbolValuesMapper;

    public StockMapper(SymbolValuesMapper symbolValuesMapper) {
        this.symbolValuesMapper = symbolValuesMapper;
    }

    public StockDomain toDomain(Stock infra) {
        return new StockDomain.StockDomainBuilder()
                .withId(infra.getId())
                .withSymbol(infra.getSymbol())
                .withSymbolValues(symbolValuesMapper.toEntity(infra.getSymbolValues()))
                .withDate(infra.getDate())
                .withVolume(infra.getVolume())
                .withOpen(infra.getOpen())
                .withClose(infra.getClose())
                .withHigh(infra.getHigh())
                .withLow(infra.getLow())
                .build();
    }

    public Stock toEntity(StockDomain entity) {
        return new Stock.StockInfraBuilder()
                .withId(entity.getId())
                .withSymbol(entity.getSymbol())
                .withDate(entity.getDate())
                .withVolume(entity.getVolume())
                .withOpen(entity.getOpen())
                .withClose(entity.getClose())
                .withHigh(entity.getHigh())
                .withLow(entity.getLow())
                .build();
    }

    public List<Stock> toList(List<StockDomain> stockDomains) {
        return stockDomains.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<StockDomain> toListDomain(List<Stock> stocks) {
        return stocks.stream()
                .map(this::toDomain)
                .toList();
    }
}
