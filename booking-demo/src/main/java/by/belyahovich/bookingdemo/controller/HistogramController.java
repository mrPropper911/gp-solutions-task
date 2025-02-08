package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.service.histogram.HistogramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotels histogram", description = "Interaction with hotels histogram")
public class HistogramController {
    private final HistogramService histogramService;

    public HistogramController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

    @Operation(
            summary = "Generate histogram",
            description = "Allows you to obtain the number of hotels" +
                    " grouped by each value of the specified parameter"
    )
    @GetMapping(value = "/histogram/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Long>> getHistogram(
            @PathVariable @Parameter(
                    description = "Search parameters",
                    example = "brand, city, county, amenities",
                    required = true) String param) {
        Map<String, Long> histogram = histogramService.getHistogram(param);
        return ResponseEntity.status(HttpStatus.OK).body(histogram);
    }
}
