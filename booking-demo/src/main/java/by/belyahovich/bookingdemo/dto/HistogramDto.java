package by.belyahovich.bookingdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HistogramDto {
    private String key;
    private Long count;
}
