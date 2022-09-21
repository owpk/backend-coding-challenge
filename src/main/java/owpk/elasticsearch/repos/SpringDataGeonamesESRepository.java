package owpk.elasticsearch.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import owpk.elasticsearch.model.GeonameSearchHint;

public interface SpringDataGeonamesESRepository extends ElasticsearchRepository<GeonameSearchHint, Long>, GeonamesESRepository {

    @Query("{" +
            "        \"bool\": {" +
            "                \"must\" : [" +
            "                        {\"range\": { \"geoname.lat\": { \"gte\": \"?0\", \"lte\": \"?1\" }}}," +
            "                        {\"range\": { \"geoname.lon\": { \"gte\": \"?2\", \"lte\": \"?3\" }}}" +
            "                        ]" +
            "         }" +
            "}")
    @Override
    Page<GeonameSearchHint> findByCoordinatesAndNameUsingCustomQuery(Float minLat, Float maxLat, Float minLong, Float maxLong, Pageable pageable);
}
