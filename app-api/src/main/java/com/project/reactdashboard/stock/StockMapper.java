package com.project.reactdashboard.stock;

import com.project.reactdashboard.entities.Stock;
import com.project.reactdashboard.entities.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.project.reactdashboard.utils.Utils.parseToISO;

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

    public StockDto toDto(Stock entity) {
        return new StockDto.StockDtoBuilder()
                .withId(entity.getId())
                .withSymbol(entity.getSymbol())
                .withName(entity.getSymbolValues().getName())
                .withDate(entity.getDate().toString())
                .withVolume(entity.getVolume())
                .withOpen(entity.getOpen())
                .withClose(entity.getClose())
                .withHigh(entity.getHigh())
                .withLow(entity.getLow())
                .build();
    }

    public List<StockDto> toListDto(List<Stock> stocks) {
        return stocks.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Stock> toList(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
