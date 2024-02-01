package com.project.reactdashboard.infrastructure.stock.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private OffsetDateTime date;

    private String symbol;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol", referencedColumnName = "symbol", insertable = false, updatable = false)
    private SymbolValues symbolValues;

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

    public SymbolValues getSymbolValues() {
        return symbolValues;
    }

    public void setSymbolValues(SymbolValues symbolValues) {
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

    public Stock() {
    }

    private Stock(StockInfraBuilder builder) {
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

    public static class StockInfraBuilder {

        private Long id;

        private OffsetDateTime date;

        private String symbol;
        
        private SymbolValues symbolValues;

        private double open;

        private double high;

        private double low;

        private double close;

        private double volume;

        public StockInfraBuilder(){
        }

        public StockInfraBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public StockInfraBuilder withDate(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public StockInfraBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public StockInfraBuilder withSymbolValues(SymbolValues symbolValues) {
            this.symbolValues = symbolValues;
            return this;
        }

        public StockInfraBuilder withOpen(double open) {
            this.open = open;
            return this;
        }

        public StockInfraBuilder withLow(double low) {
            this.low = low;
            return this;
        }

        public StockInfraBuilder withHigh(double high) {
            this.high = high;
            return this;
        }

        public StockInfraBuilder withClose(double close) {
            this.close = close;
            return this;
        }

        public StockInfraBuilder withVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Stock build(){
            return new Stock(this);
        }

    }
}
