package com.project.reactdashboard.domain.stock.mapper;

import com.project.reactdashboard.domain.stock.model.StockDomain;
import com.project.reactdashboard.app.stock.StockDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.project.reactdashboard.domain.stock.utils.Utils.parseToISO;

@Component
public class StockDomainMapper {

    public StockDomain toDomain(StockDto dto) {
        return new StockDomain.StockDomainBuilder()
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

    public StockDto toDto(StockDomain entity) {
        return new StockDto.StockDtoBuilder()
                .withId(entity.getId())
                .withSymbol(entity.getSymbol())
                .withName(entity.getSymbolValues() != null ? entity.getSymbolValues().getName() : null)
                .withDate(entity.getDate() != null ? entity.getDate().toString() : null)
                .withVolume(entity.getVolume())
                .withOpen(entity.getOpen())
                .withClose(entity.getClose())
                .withHigh(entity.getHigh())
                .withLow(entity.getLow())
                .build();
    }

    public List<StockDto> toListDto(List<StockDomain> stockDomains) {
        return stockDomains.stream()
                .map(this::toDto)
                .toList();
    }

    public List<StockDomain> toListDomain(List<StockDto> stockDtos) {
        return stockDtos.stream()
                .map(this::toDomain)
                .toList();
    }
}
