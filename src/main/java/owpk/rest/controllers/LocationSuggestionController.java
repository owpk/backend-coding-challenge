package owpk.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import owpk.rest.dto.CityLocationDto;
import owpk.services.CitySearchingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suggestions")
@AllArgsConstructor
public class LocationSuggestionController {

    private final CitySearchingService citySearchingService;

    @GetMapping
    public List<CityLocationDto> getSuggestions(@RequestParam("q") String locationName,
                                                @RequestParam("latitude") Float latitude,
                                                @RequestParam("longitude") Float longitude) {
        return citySearchingService
                .search(locationName, latitude, longitude, 8)
                .stream()
                .map(x -> {
                    var dto = new CityLocationDto();
                    dto.setLat(x.getLat());
                    dto.setLon(x.getLon());
                    dto.setName(x.getName());
                    dto.setScore(x.getScore());
                    return dto;
                }).collect(Collectors.toList());
    }
}
