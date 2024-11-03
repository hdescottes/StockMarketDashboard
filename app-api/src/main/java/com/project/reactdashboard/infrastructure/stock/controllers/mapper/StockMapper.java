package com.project.reactdashboard.infrastructure.stock.controllers.mapper;

import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import com.project.reactdashboard.domain.stock.entities.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.parseToISO;

@Component
public class StockMapper {

    public Stock toEntity(StockDto dto) {
        return new Stock.StockBuilder()
                .withId(dto.getId())
                .withSymbol(dto.getSymbol())
                .withDate(parseToISO(dto.getDate()))
                .withVolume(dto.getVolume())
                .withOpen(dto.getOpen())
                .withClose(dto.getClose())
                .withHigh(dto.getHigh())
                .withLow(dto.getLow())
                .build();
    }

    public StockDto toDto(Stock stock) {
        return new StockDto.StockDtoBuilder()
                .withId(stock.getId())
                .withSymbol(stock.getSymbol())
                .withName(stock.getSymbolValues() != null ? stock.getSymbolValues().getName() : null)
                .withDate(stock.getDate() != null ? stock.getDate().toString() : null)
                .withVolume(stock.getVolume())
                .withOpen(stock.getOpen())
                .withClose(stock.getClose())
                .withHigh(stock.getHigh())
                .withLow(stock.getLow())
                .build();
    }

    public List<StockDto> toListDto(List<Stock> stocks) {
        return stocks.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Stock> toListDomain(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
