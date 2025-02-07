package by.belyahovich.bookingdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private int houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;
}
