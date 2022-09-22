package owpk.elasticsearch.repos;

import org.springframework.data.domain.Pageable;
import owpk.domain.CityLocation;

import java.util.List;

/**
 * @author Vorobyev Vyacheslav
 */
public interface GeonamesESRepository {

    List<CityLocation> findByCoordinatesAndNameUsingCustomQuery(String name, Float lat, Float lon, Pageable pageable);
    List<CityLocation> findByCoordinatesAndNameUsingCustomQuery(String name, Pageable pageable);
}
