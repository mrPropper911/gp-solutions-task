package by.belyahovich.bookingdemo.repository.amenity;

import by.belyahovich.bookingdemo.domain.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AmenityJpaRepository extends JpaRepository<Amenity, Long> {
    Optional<Amenity> findByName(String name);
}
