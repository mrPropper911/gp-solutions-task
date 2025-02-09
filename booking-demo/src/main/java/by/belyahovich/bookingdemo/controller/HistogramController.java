package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.service.histogram.HistogramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Validated
@Tag(name = "Hotels histogram", description = "Interaction with hotels histogram")
public class HistogramController {
    private final HistogramService histogramService;

    public HistogramController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

    @Operation(
            summary = "Generate histogram",
            description = "Allows you to obtain the number of hotels" +
                    " grouped by each value of the specified parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Histogram data",
                            content = @Content(
                                    schema = @Schema(
                                            example = "{\"Minsk\": 1, \"Moskow\": 2, \"Mogilev\": 0}"
                                    )
                            )
                    )
            }
    )
    @GetMapping(value = "/histogram/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> getHistogram(
            @PathVariable @NotBlank @Parameter(
                    description = "Search parameters",
                    required = true)
            @Schema(allowableValues = {"brand", "city", "county", "amenities"}) String param) {
        Map<String, Long> histogram = histogramService.getHistogram(param);
        return ResponseEntity.status(HttpStatus.OK).body(histogram);
    }
}
