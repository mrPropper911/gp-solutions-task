package by.belyahovich.bookingdemo.dto;

import by.belyahovich.bookingdemo.domain.Address;
import by.belyahovich.bookingdemo.domain.ArrivalTime;
import by.belyahovich.bookingdemo.domain.Contacts;
import by.belyahovich.bookingdemo.domain.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(target = "address", source = "hotel", qualifiedByName = "mapAddress")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortInfoDto toHotelShortInfoDto(Hotel hotel);

    @Named("mapAddress")
    default String mapAddress(Hotel hotel) {
        if (hotel.getAddress() == null) {
            return null;
        }
        return hotel.getAddress().getFullAddress();
    }

    @Mapping(target = "address", source = "hotel.address")
    @Mapping(target = "contacts", source = "hotel.contacts")
    @Mapping(target = "arrivalTime", source = "hotel.arrivalTime")
    HotelDto toHotelDto(Hotel hotel);

    AddressDto toAddressDto(Address address);

    ContactsDto toContactsDto(Contacts contacts);

    ArrivalTimeDto toArrivalTimeDto(ArrivalTime arrivalTime);
}
