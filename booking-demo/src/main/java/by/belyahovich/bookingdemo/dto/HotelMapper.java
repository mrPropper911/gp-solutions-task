package by.belyahovich.bookingdemo.dto;

import by.belyahovich.bookingdemo.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    @Mapping(target = "address", source = "address")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    @Mapping(target = "amenities", qualifiedByName = "mapAmenitiesToStrList")
    HotelDto toHotelDto(Hotel hotel);

    @Named("mapAmenitiesToStrList")
    default List<String> mapAmenitiesToStrList(Set<Amenity> amenities) {
        if (amenities == null || amenities.isEmpty()) {
            return Collections.emptyList();
        }
        return amenities.stream()
                .map(Amenity::getName)
                .collect(Collectors.toList());
    }

    @Mapping(target = "address", expression = "java(mapAddress(hotel))")
    @Mapping(target = "phone", source = "contacts.phone")
    HotelShortInfoDto toHotelShortInfoDto(Hotel hotel);

    @Named("mapAddress")
    default String mapAddress(Hotel hotel) {
        if (hotel.getAddress() == null) {
            return "";
        }
        return hotel.getAddress().getFullAddress();
    }

    @Mapping(target = "address", source = "address")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "arrivalTime", source = "arrivalTime")
    Hotel toHotel(HotelCreateDto hotelCreateDto);

    AddressDto toAddressDto(Address address);

    ContactsDto toContactsDto(Contacts contacts);

    ArrivalTimeDto toArrivalTimeDto(ArrivalTime arrivalTime);
}
