package com.project.reactdashboard.domain.stock.model;

import java.time.OffsetDateTime;

public class StockModel {

    private Long id;

    private OffsetDateTime date;

    private String symbol;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    private SymbolValuesModel symbolValues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public SymbolValuesModel getSymbolValues() {
        return symbolValues;
    }

    public void setSymbolValues(SymbolValuesModel symbolValues) {
        this.symbolValues = symbolValues;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    private StockModel(StockModelBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.symbol = builder.symbol;
        this.symbolValues = builder.symbolValues;
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
    }

    public static class StockModelBuilder {

        private Long id;

        private OffsetDateTime date;

        private String symbol;
        
        private SymbolValuesModel symbolValues;

        private double open;

        private double high;

        private double low;

        private double close;

        private double volume;

        public StockModelBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public StockModelBuilder withDate(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public StockModelBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public StockModelBuilder withSymbolValues(SymbolValuesModel symbolValues) {
            this.symbolValues = symbolValues;
            return this;
        }

        public StockModelBuilder withOpen(double open) {
            this.open = open;
            return this;
        }

        public StockModelBuilder withLow(double low) {
            this.low = low;
            return this;
        }

        public StockModelBuilder withHigh(double high) {
            this.high = high;
            return this;
        }

        public StockModelBuilder withClose(double close) {
            this.close = close;
            return this;
        }

        public StockModelBuilder withVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public StockModel build(){
            return new StockModel(this);
        }

    }
}
