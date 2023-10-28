package com.sentrysoftware.Test2.processor.models;
public class ProcessorDataSummary {
    private String processorId;
    private String calculation;
    private double result;

    public ProcessorDataSummary() {
    }
    public ProcessorDataSummary(String processorId, String calculation, double result) {
        this.processorId = processorId;
        this.calculation = calculation;
        this.result = result;
    }
    public String getProcessorId() {
        return processorId;
    }
    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }
    public String getCalculation() {
        return calculation;
    }
    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }
    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }
}
