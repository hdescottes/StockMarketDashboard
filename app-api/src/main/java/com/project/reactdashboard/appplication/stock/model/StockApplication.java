package com.project.reactdashboard.appplication.stock.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

public class StockApplication implements Serializable {

    private Long id;

    private OffsetDateTime date;

    private String symbol;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    private SymbolValuesApplication symbolValuesApplication;

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

    public SymbolValuesApplication getSymbolValues() {
        return symbolValuesApplication;
    }

    public void setSymbolValues(SymbolValuesApplication symbolValuesApplication) {
        this.symbolValuesApplication = symbolValuesApplication;
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

    public StockApplication() {
    }

    private StockApplication(StockDomainBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.symbol = builder.symbol;
        this.symbolValuesApplication = builder.symbolValuesApplication;
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
    }

    public static class StockDomainBuilder {

        private Long id;

        private OffsetDateTime date;

        private String symbol;
        
        private SymbolValuesApplication symbolValuesApplication;

        private double open;

        private double high;

        private double low;

        private double close;

        private double volume;

        public StockDomainBuilder(){
        }

        public StockDomainBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public StockDomainBuilder withDate(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public StockDomainBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public StockDomainBuilder withSymbolValues(SymbolValuesApplication symbolValuesApplication) {
            this.symbolValuesApplication = symbolValuesApplication;
            return this;
        }

        public StockDomainBuilder withOpen(double open) {
            this.open = open;
            return this;
        }

        public StockDomainBuilder withLow(double low) {
            this.low = low;
            return this;
        }

        public StockDomainBuilder withHigh(double high) {
            this.high = high;
            return this;
        }

        public StockDomainBuilder withClose(double close) {
            this.close = close;
            return this;
        }

        public StockDomainBuilder withVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public StockApplication build(){
            return new StockApplication(this);
        }

    }
}
