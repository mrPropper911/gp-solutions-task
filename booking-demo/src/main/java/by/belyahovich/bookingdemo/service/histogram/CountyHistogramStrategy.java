package by.belyahovich.bookingdemo.service.histogram;

import by.belyahovich.bookingdemo.dto.HistogramDto;
import by.belyahovich.bookingdemo.repository.hotel.HotelRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountyHistogramStrategy implements HistogramStrategy {
    private final HotelRepository hotelRepository;

    public CountyHistogramStrategy(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HistogramDto> group() {
        return hotelRepository.getCountyHistogram();
    }

    @Override
    public HistogramParam getSupportedParam() {
        return HistogramParam.COUNTY;
    }
}
