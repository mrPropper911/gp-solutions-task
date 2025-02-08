package by.belyahovich.bookingdemo.repository.hotel;

import by.belyahovich.bookingdemo.domain.Hotel;
import by.belyahovich.bookingdemo.dto.HistogramDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HotelJpaRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
    Optional<Hotel> findByName(String name);

    @Query("SELECT NEW by.belyahovich.bookingdemo.dto.HistogramDto(h.brand, COUNT(h)) " +
            "FROM Hotel h GROUP BY h.brand")
    List<HistogramDto> getBrandHistogram();

    @Query("SELECT NEW by.belyahovich.bookingdemo.dto.HistogramDto(a.city, COUNT(a)) " +
            "FROM Address a GROUP BY a.city")
    List<HistogramDto> getCityHistogram();

    @Query("SELECT NEW by.belyahovich.bookingdemo.dto.HistogramDto(a.county, COUNT(a)) " +
            "FROM Address a GROUP BY a.county")
    List<HistogramDto> getCountyHistogram();

    @Query("SELECT NEW by.belyahovich.bookingdemo.dto.HistogramDto(am.name, COUNT(am)) " +
            "FROM Hotel h JOIN h.amenities am " +
            "GROUP BY am.name")
    List<HistogramDto> getAmenitiesHistogram();
}
