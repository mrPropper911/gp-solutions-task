package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Amenity at the hotel")
public class AmenityDto {
    @Schema(description = "Amenity name",
            example = "Free parking")
    private String name;
}
