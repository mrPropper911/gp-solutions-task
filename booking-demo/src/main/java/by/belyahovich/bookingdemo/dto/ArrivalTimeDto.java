package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Check-in and check-out times from the hotel")
public class ArrivalTimeDto {
    @Schema(description = "Check-in times",
            example = "13:00")
    private String checkIn;
    @Schema(description = "Check-out times",
            example = "12:00")
    private String checkOut;
}
