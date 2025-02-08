package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(description = "Hotel entity")
public class HotelDto {
    @Schema(description = "Unique hotel identifier",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Hotel name",
            example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "The brand to which the hotel belongs",
            example = "Hilton")
    private String brand;

    @Schema(description = "Hotel address")
    private AddressDto address;

    @Schema(description = "Hotel contact details")
    private ContactsDto contacts;

    @Schema(description = "Check-in and check-out times from the hotel")
    private ArrivalTimeDto arrivalTime;

    @Schema(description = "Amenities located at the hotel")
    private List<String> amenities = new ArrayList<>();
}
