package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelShortInfoDto {
    @Schema(description = "Unique hotel identifier",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Hotel name",
            example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Full description of the hotel",
            example = "The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in ...")
    private String description;

    @Schema(description = "Full address of the hotel",
            example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;

    @Schema(description = "Hotel phone",
            example = "+375 17 309-80-00")
    private String phone;
}
