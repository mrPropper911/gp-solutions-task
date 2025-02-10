package by.belyahovich.bookingdemo.controller;

import by.belyahovich.bookingdemo.dto.HotelCreateDto;
import by.belyahovich.bookingdemo.dto.HotelDto;
import by.belyahovich.bookingdemo.dto.HotelShortInfoDto;
import by.belyahovich.bookingdemo.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property-view")
@Validated
@Tag(name = "Hotel", description = "Interaction with hotels")
public class HotelRestController {
    private final HotelService hotelService;

    public HotelRestController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(
            summary = "Get a list of all hotels with their brief information",
            description = "Get list of HotelShortInfoDto"
    )
    @GetMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelShortInfoDto>> getAllHotels() {
        List<HotelShortInfoDto> hotelsWithShortInfo =
                hotelService.getAllHotelsWithShortInfo();
        return ResponseEntity.status(HttpStatus.OK).body(hotelsWithShortInfo);
    }

    @Operation(
            summary = "Getting extended information on a specific hotel",
            description = "Find hotel by id and return mapped HotelDto from Hotel"
    )
    @GetMapping(value = "/hotels/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelDto> getHotelById(
            @PathVariable @NotNull @Min(1) @Parameter(
                    description = "Search hotel id",
                    required = true) Long id) {
        HotelDto hotelDto = hotelService.getHotelById(id);
        return ResponseEntity.status(HttpStatus.OK).body(hotelDto);
    }

    @Operation(
            summary = "Create new hotel",
            description = "It is impossible to create a new hotel if the name is already taken"
    )
    @PostMapping(value = "/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HotelShortInfoDto> saveHotel(
            @RequestBody @Valid @Parameter(
                    description = "Entity of new hotel",
                    required = true) HotelCreateDto hotel) {
        HotelShortInfoDto hotelShortInfoDto = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelShortInfoDto);
    }

    @Operation(
            summary = "Adding a list of amenities to a hotel",
            description = "Before adding amenities, the hotel is searched for in the database"
    )
    @PostMapping(value = "/hotels/{id}/amenities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addAmenitiesToHotel(
            @PathVariable @NotNull @Min(1) @Parameter(
                    description = "ID of the hotel to which the amenities will be added",
                    required = true) Long id,
            @RequestBody @Valid @Parameter(
                    description = "List of amenities, when adding an amenity it is searched in the database," +
                            " if it is not there it will be created and added to the hotel")
            @ArraySchema(
                    schema = @Schema(description = "Amenity name",
                            example = "Free parking"),
                    arraySchema = @Schema(description = "List of amenities",
                            example = "[\"Free parking\", \"Free WiFi\", \"Non-smoking rooms\"]")
            ) List<String> amenities) {
        hotelService.addAmenitiesToHotel(id, amenities);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Search and get a list of all hotels with their brief information on the following parameters",
            description = "Search allows you to create a combination of parameters," +
                    " it also searches for a part of the searched parameter"
    )
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HotelShortInfoDto>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String county,
            @RequestParam(required = false) String amenities) {
        List<HotelShortInfoDto> hotels =
                hotelService.searchHotel(name, brand, city, county, amenities);
        return ResponseEntity.status(HttpStatus.OK).body(hotels);
    }
}
