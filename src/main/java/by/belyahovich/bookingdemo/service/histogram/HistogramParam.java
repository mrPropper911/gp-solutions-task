package by.belyahovich.bookingdemo.service.histogram;

import by.belyahovich.bookingdemo.exception.HistogramNotFoundException;

public enum HistogramParam {
    BRAND,
    CITY,
    COUNTY,
    AMENITIES;

    public static HistogramParam fromString(String param) {
        try {
            return valueOf(param.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new HistogramNotFoundException("Invalid histogram param: " + param);
        }
    }
}
