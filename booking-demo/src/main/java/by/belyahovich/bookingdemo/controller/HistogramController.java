package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.service.histogram.HistogramService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/property-view")
public class HistogramController {
    private final HistogramService histogramService;

    public HistogramController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Long>> getHistogram(@PathVariable String param) {
        Map<String, Long> histogram = histogramService.getHistogram(param);
        return ResponseEntity.status(HttpStatus.OK).body(histogram);
    }

}
