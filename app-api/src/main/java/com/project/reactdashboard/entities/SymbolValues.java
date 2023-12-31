package com.project.reactdashboard.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "symbol_values")
public class SymbolValues {

    @Id
    private String symbol;

    private String name;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymbolValues() {
    }

    private SymbolValues(SymbolValues.SymbolValuesBuilder builder) {
        this.symbol = builder.symbol;
        this.name = builder.name;
    }

    public static class SymbolValuesBuilder{

        private String symbol;

        private String name;

        public SymbolValuesBuilder(){
        }

        public SymbolValues.SymbolValuesBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public SymbolValues.SymbolValuesBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SymbolValues build(){
            return new SymbolValues(this);
        }

    }
}
