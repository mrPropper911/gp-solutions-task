package by.belyahovich.bookingdemo.repository.amenity;

import by.belyahovich.bookingdemo.domain.Amenity;

import java.util.Optional;

public class AmenityRepositoryH2Impl implements AmenityRepository{
    private final AmenityJpaRepository amenityJpaRepository;

    public AmenityRepositoryH2Impl(AmenityJpaRepository amenityJpaRepository) {
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
