package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Hidden
public class HistogramDto {
    private String key;
    private Long count;
}
