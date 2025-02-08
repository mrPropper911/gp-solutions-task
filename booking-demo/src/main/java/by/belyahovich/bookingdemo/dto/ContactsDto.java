package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Hotel contact details")
public class ContactsDto {
    @Schema(description = "Hotel phone",
            example = "+375 17 309-80-00")
    private String phone;

    @Schema(description = "Hotel email",
            example = "doubletreeminsk.info@hilton.com")
    private String email;
}
