package by.belyahovich.bookingdemo.service.histogram;

import by.belyahovich.bookingdemo.dto.HistogramDto;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AmenitiesHistogramStrategy implements HistogramStrategy {
    private final HotelRepository hotelRepository;

    public AmenitiesHistogramStrategy(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HistogramDto> group() {
        return hotelRepository.getAmenitiesHistogram();
    }

    @Override
    public HistogramParam getSupportedParam() {
        return HistogramParam.AMENITIES;
    }
}
