package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Full address of the hotel")
public class AddressDto {
    @Schema(description = "The number of the building in which the hotel is located",
            example = "9")
    @NotNull(message = "House number must not be blank")
    private int houseNumber;

    @Schema(description = "Street where the hotel is located",
            example = "Pobediteley Avenue")
    @NotBlank(message = "Street must not be blank")
    private String street;

    @Schema(description = "City where the hotel is located",
            example = "Minsk")
    @NotBlank(message = "City must not be blank")
    private String city;

    @Schema(description = "County where the hotel is located",
            example = "Belarus")
    @NotBlank(message = "County must not be blank")
    private String county;

    @Schema(description = "Postal code",
            example = "220004")
    @NotBlank(message = "Postcode must not be blank")
    private String postCode;
}
