package com.sentrysoftware.Test2.processor.utils;

import com.sentrysoftware.Test2.processor.models.HistoricalDataDTO;

import java.util.List;

public class Utils {

    public static double calculateMinValue(List<HistoricalDataDTO> historicalData) {
        double minValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::value)
                .min()
                .orElse(0.0);
        System.out.println("Min Value: " + minValue);
        return minValue;
    }
    public static double calculateMaxValue(List<HistoricalDataDTO> historicalData) {
        double maxValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::value)
                .max()
                .orElse(0.0);
        System.out.println("Max Value: " + maxValue);
        return maxValue;
    }
    public static double calculateAvgValue(List<HistoricalDataDTO> historicalData) {
        double avgValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::value)
                .average()
                .orElse(0.0);
        System.out.println("Average Value: " + avgValue);
        return avgValue;
    }
}
