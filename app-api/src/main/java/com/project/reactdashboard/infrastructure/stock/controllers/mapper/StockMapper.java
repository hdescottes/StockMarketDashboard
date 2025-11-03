package com.project.reactdashboard.infrastructure.stock.controllers.mapper;

import com.project.reactdashboard.domain.stock.model.StockModel;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import com.project.reactdashboard.infrastructure.stock.entities.Stock;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.parseToISO;

@Component
public class StockMapper {

    public StockModel toModel(StockDto dto) {
        return new StockModel.StockModelBuilder()
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

    public StockModel toModel(Stock entity) {
        return new StockModel.StockModelBuilder()
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

    public StockDto toDto(StockModel stock) {
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

    public List<StockDto> toListDto(List<StockModel> stocks) {
        return stocks.stream()
                .map(this::toDto)
                .toList();
    }

    public List<StockModel> toListDomain(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(this::toModel)
                .toList();
    }
}
