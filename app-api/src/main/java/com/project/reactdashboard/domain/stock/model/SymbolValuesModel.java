package com.project.reactdashboard.domain.stock.model;

public record SymbolValuesModel(String symbol, String name) {

    public SymbolValuesModel(SymbolValuesModelBuilder builder){
        this(builder.symbol, builder.name);
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
