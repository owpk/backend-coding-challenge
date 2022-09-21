package owpk.elasticsearch.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import owpk.elasticsearch.model.GeonameSearchHint;

/**
 * @author Vorobyev Vyacheslav
 */
public interface GeonamesESRepository {

    Page<GeonameSearchHint> findByCoordinatesAndNameUsingCustomQuery(Float minLat, Float maxLat,
                                                                     Float minLong, Float maxLong,
                                                                     Pageable pageable);
}
