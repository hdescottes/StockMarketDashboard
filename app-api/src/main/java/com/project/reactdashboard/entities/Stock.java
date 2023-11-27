package com.project.reactdashboard.entities;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Stock")
public class Stock implements Serializable {

    private String id;

    private String date;

    private String symbol;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    private Stock(StockBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.symbol = builder.symbol;
        this.open = builder.open;
        this.close = builder.close;
        this.high = builder.high;
        this.low = builder.low;
        this.volume = builder.volume;
    }

    public static class StockBuilder{

        private String id;

        private String date;

        private String symbol;

        private double open;

        private double high;

        private double low;

        private double close;

        private double volume;

        public StockBuilder(){
        }

        public StockBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public StockBuilder withDate(String date) {
            this.date = date;
            return this;
        }

        public StockBuilder withSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public StockBuilder withOpen(double open) {
            this.open = open;
            return this;
        }

        public StockBuilder withLow(double low) {
            this.low = low;
            return this;
        }

        public StockBuilder withHigh(double high) {
            this.high = high;
            return this;
        }

        public StockBuilder withClose(double close) {
            this.close = close;
            return this;
        }

        public StockBuilder withVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Stock build(){
            return new Stock(this);
        }

    }
}
