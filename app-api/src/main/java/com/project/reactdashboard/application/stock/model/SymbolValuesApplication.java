package com.project.reactdashboard.application.stock.model;

public class SymbolValuesApplication {

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

    public SymbolValuesApplication() {
    }

    private SymbolValuesApplication(SymbolValuesDomainBuilder builder) {
        this.symbol = builder.symbol;
        this.name = builder.name;
    }

    public static class SymbolValuesDomainBuilder {

        private String symbol;

        private String name;

        public SymbolValuesDomainBuilder(){
        }

        public SymbolValuesDomainBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public SymbolValuesDomainBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SymbolValuesApplication build(){
            return new SymbolValuesApplication(this);
        }

    }
}
