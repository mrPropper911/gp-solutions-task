package by.belyahovich.bookingdemo.repository.amenity;

import by.belyahovich.bookingdemo.domain.Amenity;

import java.util.Optional;

public interface AmenityRepository {
    Amenity save(Amenity amenity);

    Optional<Amenity> findByName(String name);
}
