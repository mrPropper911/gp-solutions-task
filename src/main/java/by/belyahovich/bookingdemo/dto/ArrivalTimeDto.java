package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Check-in and check-out times from the hotel")
public class ArrivalTimeDto {
    @Schema(description = "Check-in times",
            example = "13:00")
    @NotBlank(message = "Check In must not be blank")
    private String checkIn;

    @Schema(description = "Check-out times",
            example = "12:00")
    private String checkOut;
}
