package owpk.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import owpk.domain.CityLocation;
import owpk.elasticsearch.repos.GeonamesESRepository;
import owpk.services.CitySearchingService;

import java.util.List;

/**
 * @author Vorobyev Vyacheslav
 */
@AllArgsConstructor
@Service
public class CitySearchingServiceImpl implements CitySearchingService {

    private final GeonamesESRepository esRepository;

    @Override
    public List<CityLocation> search(String searchName, Float lat, Float lon, Integer totalShow) {
        var pageable = Pageable.ofSize(totalShow);

        if (lat == null || lon == null)
            return esRepository.findByCoordinatesAndNameUsingCustomQuery(
                    searchName, pageable);
        else
            return esRepository.findByCoordinatesAndNameUsingCustomQuery(
                    searchName, lat, lon, pageable);
    }
}
