package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity with the required fields to create a hotel")
public class HotelCreateDto {
    @Schema(description = "Hotel name",
            example = "DoubleTree by Hilton Minsk")
    @NotBlank(message = "Name must not be blank")
    private String name;

    @Schema(description = "Full description of the hotel",
            example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in ...")
    private String description;

    @Schema(description = "The brand to which the hotel belongs",
            example = "Hilton")
    @NotBlank(message = "Brand must not be blank")
    private String brand;

    @Schema(description = "Hotel address")
    @Valid
    private AddressDto address;

    @Schema(description = "Hotel contact details")
    @Valid
    private ContactsDto contacts;

    @Schema(description = "Check-in and check-out times from the hotel")
    @Valid
    private ArrivalTimeDto arrivalTime;
}
