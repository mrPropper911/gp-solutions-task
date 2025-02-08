package by.belyahovich.bookingdemo.repository.hotel;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HistogramDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {
    Hotel save (Hotel hotel);

    List<Hotel> findAll();

    List<Hotel> findAll(Specification<Hotel> specification);

    boolean existsByName(String name);

    Optional<Hotel> findById(Long id);

    List<HistogramDto> getBrandHistogram();

    List<HistogramDto> getCityHistogram();

    List<HistogramDto> getCountyHistogram();

    List<HistogramDto> getAmenitiesHistogram();
}
