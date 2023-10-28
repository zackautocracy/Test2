package com.sentrysoftware.Test2.processor.services;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProcessorService {
    public List<String> getAllProcessorIds() {
        try {
            String url = "https://xdemo.sentrysoftware.com/rest/namespace/NT_CPU";
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonResponse = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }
            reader.close();
            connection.disconnect();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse.toString());
            JsonNode subnodes = rootNode.get("subnodes");

            List<String> processorIds = new ArrayList<>();
            for (JsonNode node : subnodes) {
                String processorId = node.asText();
                processorIds.add(processorId);
            }

            processorIds.remove("CPU__Total");

            return processorIds;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve processor data", e);
        }
    }
}
