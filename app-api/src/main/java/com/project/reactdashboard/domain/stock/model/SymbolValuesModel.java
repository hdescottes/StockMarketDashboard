package com.project.reactdashboard.domain.stock.model;

public class SymbolValuesModel {

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

    private SymbolValuesModel(SymbolValuesModelBuilder builder) {
        this.symbol = builder.symbol;
        this.name = builder.name;
    }

    public static class SymbolValuesModelBuilder {

        private String symbol;

        private String name;

        public SymbolValuesModelBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public SymbolValuesModelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SymbolValuesModel build(){
            return new SymbolValuesModel(this);
        }

    }
}
