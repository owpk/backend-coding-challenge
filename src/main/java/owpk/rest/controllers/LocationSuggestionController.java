package owpk.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import owpk.services.CitySearchingService;

@RestController
@RequestMapping("/suggestions")
@AllArgsConstructor
public class LocationSuggestionController {

    private final CitySearchingService citySearchingService;

    @GetMapping
    public void getSugs(@PathVariable("q") String locationName,
                        @PathVariable("latitude") Float latitude,
                        @PathVariable("longitude") Float longitude) {
        citySearchingService.search(locationName, latitude, longitude, 10);
    }
}
