package by.belyahovich.bookingdemo.repository.amenity;

import by.belyahovich.bookingdemo.domain.Amenity;

import java.util.Optional;

public class AmenityRepositoryMySqlImpl implements AmenityRepository {
    private final AmenityJpaRepository amenityJpaRepository;

    public AmenityRepositoryMySqlImpl(AmenityJpaRepository amenityJpaRepository) {
        this.amenityJpaRepository = amenityJpaRepository;
    }

    @Override
    public Amenity save(Amenity amenity) {
        return amenityJpaRepository.save(amenity);
    }

    @Override
    public Optional<Amenity> findByName(String name) {
        return amenityJpaRepository.findByName(name);
    }
}
