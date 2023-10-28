package com.sentrysoftware.Test2.processor.models;
public class HistoricalDataDTO {
    private long timestamp;
    private double value;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HistoricalDataDTO{" +
                "timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}












