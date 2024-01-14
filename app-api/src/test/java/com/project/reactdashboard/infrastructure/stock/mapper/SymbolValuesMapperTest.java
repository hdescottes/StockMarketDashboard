package com.project.reactdashboard.infrastructure.stock.mapper;

import com.project.reactdashboard.domain.stock.model.SymbolValuesDomain;
import com.project.reactdashboard.infrastructure.stock.model.SymbolValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.project.reactdashboard.ObjectRandomizer.randomSymbolValues;
import static com.project.reactdashboard.ObjectRandomizer.randomSymbolValuesDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolValuesMapperTest {

    private SymbolValuesMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new SymbolValuesMapper();
    }

    @Test
    void toEntity() {
        SymbolValuesDomain symbolValuesDomain = randomSymbolValuesDomain();

        SymbolValues infra = mapper.toInfra(symbolValuesDomain);

        assertEquals(symbolValuesDomain.getSymbol(), infra.getSymbol());
        assertEquals(symbolValuesDomain.getName(), infra.getName());
    }

    @Test
    void toInfra() {
        SymbolValues symbolValues = randomSymbolValues();

        SymbolValuesDomain domain = mapper.toEntity(symbolValues);

        assertEquals(symbolValues.getSymbol(), domain.getSymbol());
        assertEquals(symbolValues.getName(), domain.getName());
    }
}
