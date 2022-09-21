package owpk.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import owpk.elasticsearch.repos.GeonamesESRepository;
import owpk.services.CitySearchingService;

import java.util.stream.Collectors;

/**
 * @author Vorobyev Vyacheslav
 */
@AllArgsConstructor
@Service
public class CitySearchingServiceImpl implements CitySearchingService {

    private final GeonamesESRepository esRepository;

    @Override
    public void search(String searchName, Float lat, Float lon, Integer coordsSpread) {
        var result = esRepository.findByCoordinatesAndNameUsingCustomQuery(
                lat - coordsSpread, lat + coordsSpread,
                lon - coordsSpread, lon + coordsSpread,
                Pageable.ofSize(8));
        var r = result.get().collect(Collectors.toList());
        System.out.println("DATA: ");
        r.forEach(System.out::println);
    }
}
