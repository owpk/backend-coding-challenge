package owpk.elasticsearch.repos;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import owpk.domain.CityLocation;
import owpk.elasticsearch.model.ElasticsearchGeoname;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
@AllArgsConstructor
@Slf4j
public class GeonamesESRepositoryImpl implements GeonamesESRepository {

    private final ElasticsearchOperations elasticsearchRestTemplate;

    @Override
    public List<CityLocation> findByCoordinatesAndNameUsingCustomQuery(String name, Float lat,
                                                                       Float lon, Pageable pageable) {

        var geoDistanceSort = SortBuilders.geoDistanceSort(
                "location", new GeoPoint(lat, lon))
                .unit(DistanceUnit.KILOMETERS)
                .order(SortOrder.ASC);

        var nativeSearchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(getMatchQuery(name))
                .withSorts(geoDistanceSort)
                .build();

        return createRequest(nativeSearchQuery);
    }

    @Override
    public List<CityLocation> findByCoordinatesAndNameUsingCustomQuery(String name, Pageable pageable) {
        var nativeSearchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(getMatchQuery(name))
                .build();
        return createRequest(nativeSearchQuery);
    }

    private List<CityLocation> createRequest(Query query) {
        return elasticsearchRestTemplate
                .search(query, ElasticsearchGeoname.class)
                .get()
                .map(this::mapElasticDocToEntity)
                .collect(Collectors.toList());
    }

    private MatchPhrasePrefixQueryBuilder getMatchQuery(String text) {
        return QueryBuilders.matchPhrasePrefixQuery("name", text);
    }

    private CityLocation mapElasticDocToEntity(SearchHit<ElasticsearchGeoname> elasticsearchGeoname) {
        var content = elasticsearchGeoname.getContent();
        var cl = new CityLocation();
        cl.setName(content.getName() + "," + content.getCountry());
        cl.setLat(content.getLat());
        cl.setLon(content.getLon());
        cl.setScore(elasticsearchGeoname.getScore());
        return cl;
    }
}