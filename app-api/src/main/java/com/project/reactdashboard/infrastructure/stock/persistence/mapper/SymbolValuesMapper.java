package com.project.reactdashboard.infrastructure.stock.persistence.mapper;

import com.project.reactdashboard.appplication.stock.model.SymbolValuesApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.SymbolValues;
import org.springframework.stereotype.Component;

@Component
public class SymbolValuesMapper {

    public SymbolValuesApplication toApplication(SymbolValues symbolValues) {
        return new SymbolValuesApplication.SymbolValuesDomainBuilder()
                .withSymbol(symbolValues.getSymbol())
                .withName(symbolValues.getName())
                .build();
    }
}
