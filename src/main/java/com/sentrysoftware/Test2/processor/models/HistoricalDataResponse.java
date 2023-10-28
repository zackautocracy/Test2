package com.sentrysoftware.Test2.processor.models;
import java.util.List;
public class HistoricalDataResponse {
    private List<HistoricalDataDTO> history;

    public List<HistoricalDataDTO> getHistory() {
        return history;
    }

    public void setHistory(List<HistoricalDataDTO> history) {
        this.history = history;
    }
}
