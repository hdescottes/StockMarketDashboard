package com.project.reactdashboard.domain.stock.model;

import java.time.OffsetDateTime;

public record StockModel(Long id, OffsetDateTime date, String symbol, double open, double high, double low,
                         double close, double volume, SymbolValuesModel symbolValues) {

    public StockModel(StockModelBuilder builder){
        this(builder.id, builder.date, builder.symbol, builder.open, builder.high, builder.low,
                builder.close, builder.volume, builder.symbolValues);
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
