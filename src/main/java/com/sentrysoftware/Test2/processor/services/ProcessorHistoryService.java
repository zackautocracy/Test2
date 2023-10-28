package com.sentrysoftware.Test2.processor.services;
import com.sentrysoftware.Test2.processor.models.HistoricalDataDTO;
import com.sentrysoftware.Test2.processor.models.HistoricalDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class ProcessorHistoryService {
    @Autowired
    private RestTemplate restTemplate;
    public CompletableFuture<List<HistoricalDataDTO>> getHistoricalDataForProcessor(String processorId, int max) {
        String url = "https://xdemo.sentrysoftware.com/rest/console/NT_CPU/" + processorId + "/CPUprcrProcessorTimePercent?max=" + max;

        ResponseEntity<HistoricalDataResponse> response = restTemplate.getForEntity(url, HistoricalDataResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            HistoricalDataResponse historicalDataResponse = response.getBody();
            List<HistoricalDataDTO> historicalData = historicalDataResponse.getHistory();

            String historicalDataString = historicalData.toString();
            System.out.println("Historical Data: " + historicalDataString);

            return CompletableFuture.completedFuture(historicalData);
        } else {
            return CompletableFuture.completedFuture(new ArrayList<>());
        }
    }
    public double calculateMinValue(List<HistoricalDataDTO> historicalData) {
        double minValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::getValue)
                .min()
                .orElse(0.0);
        System.out.println("Min Value: " + minValue);
        return minValue;
    }
    public double calculateMaxValue(List<HistoricalDataDTO> historicalData) {
        double maxValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::getValue)
                .max()
                .orElse(0.0);
        System.out.println("Max Value: " + maxValue);
        return maxValue;
    }
    public double calculateAvgValue(List<HistoricalDataDTO> historicalData) {
        double avgValue = historicalData.stream()
                .mapToDouble(HistoricalDataDTO::getValue)
                .average()
                .orElse(0.0);
        System.out.println("Average Value: " + avgValue);
        return avgValue;
    }


}
