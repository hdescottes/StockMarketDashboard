package com.project.reactdashboard.application.stock.mapper;

import com.project.reactdashboard.application.stock.model.StockApplication;
import com.project.reactdashboard.infrastructure.stock.controllers.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.project.reactdashboard.domain.stock.Date.parseToISO;

@Component
public class StockApplicationMapper {

    public StockApplication toApplication(StockDto dto) {
        return new StockApplication.StockDomainBuilder()
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

    public StockDto toDto(StockApplication app) {
        return new StockDto.StockDtoBuilder()
                .withId(app.getId())
                .withSymbol(app.getSymbol())
                .withName(app.getSymbolValues() != null ? app.getSymbolValues().getName() : null)
                .withDate(app.getDate() != null ? app.getDate().toString() : null)
                .withVolume(app.getVolume())
                .withOpen(app.getOpen())
                .withClose(app.getClose())
                .withHigh(app.getHigh())
                .withLow(app.getLow())
                .build();
    }

    public List<StockDto> toListDto(List<StockApplication> stockApplications) {
        return stockApplications.stream()
                .map(this::toDto)
                .toList();
    }

    public List<StockApplication> toListApplication(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(this::toApplication)
                .toList();
    }
}
