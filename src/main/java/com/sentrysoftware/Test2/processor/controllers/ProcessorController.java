package com.sentrysoftware.Test2.processor.controllers;

import com.sentrysoftware.Test2.processor.models.HistoricalDataDTO;
import com.sentrysoftware.Test2.processor.models.ProcessorDataSummary;
import com.sentrysoftware.Test2.processor.services.ProcessorHistoryService;
import com.sentrysoftware.Test2.processor.services.ProcessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "processors")
@Tag(name = "Processor Controller", description = "Processor Controller")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    @Autowired
    private ProcessorHistoryService processorHistoryService;

    @Operation(summary = "Get a list of processor IDs")
    @GetMapping
    public List<String> getProcessorData() {
        return processorService.getAllProcessorIds();
    }

    @Operation(summary = "Get historical data summary for a processor")
    @ApiResponse(
            responseCode = "200",
            description = "Historical data summary",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessorDataSummary.class))
    )
    @GetMapping("/CPUprcrProcessorTimePercent")
    public CompletableFuture<ProcessorDataSummary> getHistoricalDataSummary(
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Processor ID",
                    required = true,
                    example = "12345"
            )
            @RequestParam String processorId,

            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Maximum number of data points",
                    required = true,
                    example = "100"
            )
            @RequestParam int max,

            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Calculation type (min, max, avg)",
                    required = true,
                    example = "min"
            )
            @RequestParam String calculation) {

        CompletableFuture<List<HistoricalDataDTO>> historicalData = processorHistoryService.getHistoricalDataForProcessor(processorId, max);

        return historicalData.thenApply(data -> {
            double result = 0.0;

            switch (calculation) {
                case "min":
                    result = processorHistoryService.calculateMinValue(data);
                    break;
                case "max":
                    result = processorHistoryService.calculateMaxValue(data);
                    break;
                case "avg":
                    result = processorHistoryService.calculateAvgValue(data);
                    break;
                default:
                    System.out.println("calculation type not compatible");
                    break;
            }

            return new ProcessorDataSummary(processorId, calculation, result);
        });
    }
}
