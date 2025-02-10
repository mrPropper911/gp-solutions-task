package by.belyahovich.bookingdemo.service.histogram;

import by.belyahovich.bookingdemo.dto.HistogramDto;

import java.util.List;

public interface HistogramStrategy {
    List<HistogramDto> group();

    HistogramParam getSupportedParam();
}
