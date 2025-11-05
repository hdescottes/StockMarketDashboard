package com.project.reactdashboard.infrastructure.stock.controllers;

import java.io.Serializable;

public class StockDto implements Serializable {

    private Long id;

    private String date;

    private String symbol;

    private String name;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    public StockDto() {
    }

    private StockDto(StockDtoBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.symbol = builder.symbol;
        this.name = builder.name;
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
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
