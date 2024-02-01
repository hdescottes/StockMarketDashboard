package com.project.reactdashboard.infrastructure.stock.persistence.mapper;

import com.project.reactdashboard.appplication.stock.model.SymbolValuesApplication;
import com.project.reactdashboard.infrastructure.stock.persistence.entities.SymbolValues;
import com.project.reactdashboard.infrastructure.stock.persistence.mapper.SymbolValuesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.project.reactdashboard.ObjectRandomizer.randomSymbolValues;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymbolValuesMapperTest {

    private SymbolValuesMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new SymbolValuesMapper();
    }

    @Test
    void toInfra() {
        SymbolValues symbolValues = randomSymbolValues();

        SymbolValuesApplication domain = mapper.toApplication(symbolValues);

        assertEquals(symbolValues.getSymbol(), domain.getSymbol());
        assertEquals(symbolValues.getName(), domain.getName());
    }
}
