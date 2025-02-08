package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Full address of the hotel")
public class AddressDto {
    @Schema(description = "The number of the building in which the hotel is located",
            example = "9")
    private int houseNumber;

    @Schema(description = "Street where the hotel is located",
            example = "Pobediteley Avenue")
    private String street;

    @Schema(description = "City where the hotel is located",
            example = "Minsk")
    private String city;

    @Schema(description = "County where the hotel is located",
            example = "Belarus")
    private String county;

    @Schema(description = "Postal code",
            example = "220004")
    private String postCode;
}
