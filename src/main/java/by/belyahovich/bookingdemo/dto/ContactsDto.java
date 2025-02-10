package by.belyahovich.bookingdemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Hotel contact details")
public class ContactsDto {
    @Schema(description = "Hotel phone",
            example = "+375 17 309-80-00")
    @NotBlank(message = "Phone must not be blank")
    private String phone;

    @Schema(description = "Hotel email",
            example = "doubletreeminsk.info@hilton.com")
    @Email(message = "Email must not be blank")
    private String email;
}
