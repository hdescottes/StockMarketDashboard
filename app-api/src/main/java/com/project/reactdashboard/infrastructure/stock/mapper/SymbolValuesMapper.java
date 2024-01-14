package com.project.reactdashboard.infrastructure.stock.mapper;

import com.project.reactdashboard.domain.stock.model.SymbolValuesDomain;
import com.project.reactdashboard.infrastructure.stock.model.SymbolValues;
import org.springframework.stereotype.Component;

@Component
public class SymbolValuesMapper {

    public SymbolValues toInfra(SymbolValuesDomain symbolValuesDomain) {
        return new SymbolValues.SymbolValuesBuilder()
                .withSymbol(symbolValuesDomain.getSymbol())
                .withName(symbolValuesDomain.getName())
                .build();
    }

    public SymbolValuesDomain toEntity(SymbolValues symbolValues) {
        return new SymbolValuesDomain.SymbolValuesDomainBuilder()
                .withSymbol(symbolValues.getSymbol())
                .withName(symbolValues.getName())
                .build();
    }
}
