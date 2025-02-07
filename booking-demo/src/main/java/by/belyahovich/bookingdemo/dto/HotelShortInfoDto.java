package by.belyahovich.bookingdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelShortInfoDto {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
