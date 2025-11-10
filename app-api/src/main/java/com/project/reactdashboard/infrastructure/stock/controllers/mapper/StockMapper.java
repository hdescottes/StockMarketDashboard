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
                .withId(dto.id())
                .withSymbol(dto.symbol())
                .withDate(parseToISO(dto.date()))
                .withVolume(dto.volume())
                .withOpen(dto.open())
                .withClose(dto.close())
                .withHigh(dto.high())
                .withLow(dto.low())
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
                .withId(stock.id())
                .withSymbol(stock.symbol())
                .withName(stock.symbolValues() != null ? stock.symbolValues().name() : null)
                .withDate(stock.date() != null ? stock.date().toString() : null)
                .withVolume(stock.volume())
                .withOpen(stock.open())
                .withClose(stock.close())
                .withHigh(stock.high())
                .withLow(stock.low())
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
