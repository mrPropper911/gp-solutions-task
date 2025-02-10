package by.belyahovich.bookingdemo.dto;

import by.belyahovich.bookingdemo.domain.Amenity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AmenityMapper {
    AmenityMapper INSTANCE = Mappers.getMapper(AmenityMapper.class);

    AmenityDto toAmenityDto(Amenity amenity);

    @InheritInverseConfiguration
    Amenity toAmenity(AmenityDto amenityDto);
}
