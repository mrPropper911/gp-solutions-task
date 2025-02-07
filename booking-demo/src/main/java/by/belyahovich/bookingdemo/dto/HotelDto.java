package by.belyahovich.bookingdemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class HotelDto {
    private Long id;
    private String name;
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private Set<String> amenities;
}
