package by.belyahovich.bookingdemo.service.histogram;

import by.belyahovich.bookingdemo.dto.HistogramDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HistogramService {
    private final Map<HistogramParam, HistogramStrategy> strategies;

    public HistogramService(List<HistogramStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        HistogramStrategy::getSupportedParam,
                        Function.identity()
                ));
    }

    public Map<String, Long> getHistogram(String param) {
        var histogramParam = HistogramParam.fromString(param);
        var histogramStrategy = strategies.get(histogramParam);
        var histogramDtoList = histogramStrategy.group();
        return convertToHistogram(histogramDtoList);
    }

    private Map<String, Long> convertToHistogram(List<HistogramDto> histogramDtoList) {
        Map<String, Long> histogram = new HashMap<>();
        for (var h : histogramDtoList) {
            histogram.put(h.getKey(), h.getCount());
        }
        return histogram;
    }
}
