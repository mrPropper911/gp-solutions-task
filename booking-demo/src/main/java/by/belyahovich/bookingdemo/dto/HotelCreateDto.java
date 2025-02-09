package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Entity with the required fields to create a hotel")
public class HotelCreateDto {
    @Schema(description = "Hotel name",
            example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Full description of the hotel",
            example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in ...")
    private String description;

    @Schema(description = "The brand to which the hotel belongs",
            example = "Hilton")
    private String brand;

    @Schema(description = "Hotel address")
    private AddressDto address;

    @Schema(description = "Hotel contact details")
    private ContactsDto contacts;

    @Schema(description = "Check-in and check-out times from the hotel")
    private ArrivalTimeDto arrivalTime;
}
