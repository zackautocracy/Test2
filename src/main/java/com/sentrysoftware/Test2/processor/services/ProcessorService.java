package com.sentrysoftware.Test2.processor.services;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorService {
    private static final String DATA_URL = "https://xdemo.sentrysoftware.com/rest/namespace/NT_CPU";

    public List<String> getAllProcessorIds() {
        try {
            JsonNode rootNode = fetchJsonData(DATA_URL);
            JsonNode subnodes = rootNode.get("subnodes");

            List<String> processorIds = new ArrayList<>();
            for (JsonNode node : subnodes) {
                String processorId = node.asText();
                if (!processorId.equals("CPU__Total")) {
                    processorIds.add(processorId);
                }
            }
            return processorIds;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve processor data", e);
        }
    }

    private static JsonNode fetchJsonData(String url) throws IOException {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readTree(inputStream);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
