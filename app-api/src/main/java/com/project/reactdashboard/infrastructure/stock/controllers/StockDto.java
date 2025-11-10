package com.project.reactdashboard.infrastructure.stock.controllers;

import java.io.Serializable;

public record StockDto(Long id, String date, String symbol,String name,  double open, double high, double low,
                       double close, double volume) implements Serializable {

    public StockDto(StockDtoBuilder builder){
        this(builder.id, builder.date, builder.symbol, builder.name, builder.open, builder.high, builder.low,
                builder.close, builder.volume);
    }

    public static class StockDtoBuilder{

        private Long id;

        private String date;

        private String symbol;

        private String name;

        private double open;

        private double high;

        private double low;

        private double close;

        private double volume;

        public StockDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public StockDtoBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public StockDtoBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public StockDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public StockDtoBuilder withOpen(double open) {
            this.open = open;
            return this;
        }

        public StockDtoBuilder withLow(double low) {
            this.low = low;
            return this;
        }

        public StockDtoBuilder withHigh(double high) {
            this.high = high;
            return this;
        }

        public StockDtoBuilder withClose(double close) {
            this.close = close;
            return this;
        }

        public StockDtoBuilder withVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public StockDto build(){
            return new StockDto(this);
        }

    }
}
