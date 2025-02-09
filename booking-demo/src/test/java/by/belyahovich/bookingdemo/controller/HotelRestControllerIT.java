package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.dto.*;
import by.belyahovich.bookingdemo.service.HotelService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("h2-test")
@AutoConfigureMockMvc
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HotelRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelService hotelService;

    @Test
    @DisplayName("GET /property-view/hotels returns an HTTP response" +
            " with a 200 OK status and a list of HotelShortInfoDto")
    void getAllHotels_ReturnsValidResponseEntity() throws Exception {
        var hotelCreateDto = new HotelCreateDto("Name_1", "Description_1", "Brand_1",
                new AddressDto(1, "Street_1", "City_1", "County_1", "PostCode_1"),
                new ContactsDto("Phone_1", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_1", "CheckOut_2"));

        var savedHotel = hotelService.saveHotel(hotelCreateDto);
        assertNotNull(savedHotel);

        mockMvc.perform(get("/property-view/hotels"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "name": "Name_1",
                                        "description": "Description_1",
                                        "address": "1 Street_1, City_1, PostCode_1, County_1",
                                        "phone": "Phone_1"
                                    }
                                ]
                                """)
                );
    }

    @Test
    @DisplayName("GET /property-view/hotels/{id} returns an HTTP response" +
            " with a 200 OK status and HotelDto")
    void getHotelById_PayloadIsValid_ReturnValidResponseEntity() throws Exception {
        var hotelCreateDto = new HotelCreateDto("Name_1", "Description_1", "Brand_1",
                new AddressDto(1, "Street_1", "City_1", "County_1", "PostCode_1"),
                new ContactsDto("Phone_1", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_1", "CheckOut_2"));

        var savedHotel = hotelService.saveHotel(hotelCreateDto);
        assertNotNull(savedHotel);

        mockMvc.perform(get("/property-view/hotels/1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                        {
                                            "id": 1,
                                            "name": "Name_1",
                                            "brand": "Brand_1",
                                            "address": {
                                                "houseNumber": 1,
                                                "street": "Street_1",
                                                "city": "City_1",
                                                "county": "County_1",
                                                "postCode": "PostCode_1"
                                            },
                                            "contacts": {
                                                "phone": "Phone_1",
                                                "email": "email_1@mail.com"
                                            },
                                            "arrivalTime": {
                                                "checkIn": "CheckIn_1",
                                                "checkOut": "CheckOut_2"
                                            },
                                            "amenities": []
                                        }
                                """)
                );
    }

    @Test
    @DisplayName("GET /property-view/hotels/{id} returns an HTTP response" +
            " with a 400 BAD_REQUEST status")
    void getHotelById_PayloadNotValid_ReturnValidResponseEntity() throws Exception {
        mockMvc.perform(get("/property-view/hotels/99"))
                .andExpectAll(
                        status().isNotFound(),
                        content().string("Hotel with ID: 99 not found")
                );
    }

    @Test
    @DisplayName("POST /property-view/hotels create new hotel, returns an HTTP response" +
            " with a 200 OK status and HotelShortInfoDto")
    void saveHotel_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "name": "Name_1",
                                        "description": "Description_1",
                                        "brand": "Brand_1",
                                        "address":
                                            {
                                                "houseNumber": 1,
                                                "street": "Street_1",
                                                "city": "City_1",
                                                "county": "County_1",
                                                "postCode": "PostCode_1"
                                            },
                                        "contacts":
                                            {
                                                "phone": "Phone_1",
                                                "email": "email_1@mail.com"
                                            },
                                        "arrivalTime":
                                            {
                                                "checkIn": "CheckIn_1",
                                                "checkOut": "CheckOut_2"
                                            }
                                    }
                                """))
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                            {
                                                "id": 1,
                                                "name": "Name_1",
                                                "description": "Description_1",
                                                "address": "1 Street_1, City_1, PostCode_1, County_1",
                                                "phone": "Phone_1"
                                            }
                                """)
                );

        assertEquals(1, hotelService.getAllHotelsWithShortInfo().size());

        var hotel = hotelService.getHotelById(1L);
        assertNotNull(hotel.getId());
        assertEquals("Name_1", hotel.getName());
        assertEquals("Brand_1", hotel.getBrand());
    }

    @Test
    @DisplayName("POST /property-view/hotels request with blank field returns an HTTP response" +
            " with a 400 BAD_REQUEST status")
    void saveHotel_PayloadNotValid_ReturnsValidResponseEntity() throws Exception {
        mockMvc.perform(post("/property-view/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    {
                                        "name": "Name_1",
                                        "description": "Description_1",
                                        "address":
                                            {
                                                "houseNumber": 1,
                                                "street": "Street_1",
                                                "city": "City_1",
                                                "county": "County_1",
                                                "postCode": "PostCode_1"
                                            },
                                        "contacts":
                                            {
                                                "phone": "Phone_1",
                                                "email": "email_1@mail.com"
                                            },
                                        "arrivalTime":
                                            {
                                                "checkIn": "CheckIn_1",
                                                "checkOut": "CheckOut_2"
                                            }
                                    }
                                """))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                            {
                                                "brand": "Brand must not be blank"
                                            }
                                """)
                );

        assertEquals(0, hotelService.getAllHotelsWithShortInfo().size());
    }

    @Test
    @DisplayName("POST /property-view/hotels/{id}/amenities returns an HTTP response" +
            " with a 204 NO_CONTENT status")
    void addAmenitiesToHotel_shouldAddAmenitiesToEntity() throws Exception {
        var hotelCreateDto = new HotelCreateDto("Name_1", "Description_1", "Brand_1",
                new AddressDto(1, "Street_1", "City_1", "County_1", "PostCode_1"),
                new ContactsDto("Phone_1", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_1", "CheckOut_2"));

        var savedHotel = hotelService.saveHotel(hotelCreateDto);
        assertNotNull(savedHotel);

        mockMvc.perform(post("/property-view/hotels/1/amenities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                    [
                                        "Free parking",
                                        "Free WiFi",
                                        "Non-smoking rooms",
                                        "Concierge",
                                        "On-site restaurant",
                                        "Fitness center",
                                        "Pet-friendly rooms",
                                        "Room service",
                                        "Business center",
                                        "Meeting rooms"
                                    ]
                                """))
                .andExpect(status().isNoContent());

        var hotel = hotelService.getHotelById(1L);
        assertNotNull(hotel);
        assertEquals(10, hotel.getAmenities().size());
    }

    @Test
    @DisplayName("GET /search returns an HTTP response" +
            " with a 200 OK status and a list of HotelShortInfoDto")
    void searchHotels_ReturnsValidResponseEntity() throws Exception {
        var hotelCreateDto = new HotelCreateDto("Name_1", "Description_1", "Brand_1",
                new AddressDto(1, "Street_1", "City_1", "County_1", "PostCode_1"),
                new ContactsDto("Phone_1", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_1", "CheckOut_2"));

        var savedHotel = hotelService.saveHotel(hotelCreateDto);
        assertNotNull(savedHotel);

        var hotelCreateDto2 = new HotelCreateDto("Name_2", "Description_2", "Brand_2",
                new AddressDto(2, "Street_2", "City_2", "County_2", "PostCode_2"),
                new ContactsDto("Phone_2", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_2", "CheckOut_2"));

        var savedHotel2 = hotelService.saveHotel(hotelCreateDto2);
        assertNotNull(savedHotel2);

        mockMvc.perform(get("/property-view/search?name=ame"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "name": "Name_1",
                                        "description": "Description_1",
                                        "address": "1 Street_1, City_1, PostCode_1, County_1",
                                        "phone": "Phone_1"
                                    },
                                    {
                                        "id": 2,
                                        "name": "Name_2",
                                        "description": "Description_2",
                                        "address": "2 Street_2, City_2, PostCode_2, County_2",
                                        "phone": "Phone_2"
                                    }
                                ]
                                """)
                );
    }

    @Test
    @DisplayName("GET /search returns an HTTP response" +
            " with a 200 OK status and a list of HotelShortInfoDto")
    void getHistogram_ReturnsValidResponseEntity() throws Exception {
        var hotelCreateDto = new HotelCreateDto("Name_1", "Description_1", "Brand_1",
                new AddressDto(1, "Street_1", "City_1", "County_1", "PostCode_1"),
                new ContactsDto("Phone_1", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_1", "CheckOut_2"));

        var savedHotel = hotelService.saveHotel(hotelCreateDto);
        assertNotNull(savedHotel);

        var hotelCreateDto2 = new HotelCreateDto("Name_2", "Description_2", "Brand_2",
                new AddressDto(2, "Street_2", "City_2", "County_2", "PostCode_2"),
                new ContactsDto("Phone_2", "email_1@mail.com"),
                new ArrivalTimeDto("CheckIn_2", "CheckOut_2"));

        var savedHotel2 = hotelService.saveHotel(hotelCreateDto2);
        assertNotNull(savedHotel2);

        mockMvc.perform(get("/property-view/histogram/brand"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json("""
                                {
                                    "Brand_1": 1,
                                    "Brand_2": 1
                                }
                                """)
                );
    }
}