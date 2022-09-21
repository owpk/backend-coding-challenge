package owpk.elasticsearch.repos;

import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;
import owpk.elasticsearch.model.GeonameSearchHint;

import java.util.stream.Collectors;


@Repository
@Primary
@AllArgsConstructor
public class GeonamesESRepositoryImpl implements GeonamesESRepository {

    private final ElasticsearchOperations elasticsearchRestTemplate;

    @Override
    public Page<GeonameSearchHint> findByCoordinatesAndNameUsingCustomQuery(Float minLat, Float maxLat,
                                                                            Float minLong, Float maxLong,
                                                                            Pageable pageable) {
        var filter = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("geoname.lat").gte(minLat).lte(maxLat))
                .must(QueryBuilders.rangeQuery("geoname.lon").gte(minLong).lte(maxLong));
        var nativeSearchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(filter)
                .withQuery(QueryBuilders.functionScoreQuery(QueryBuilders.matchAllQuery()))
                .build();
        var result = elasticsearchRestTemplate.search(nativeSearchQuery, GeonameSearchHint.class)
                .get().map(x -> {
                    var article = x.getContent();
                    article.setScore(x.getScore());
                    return article;
                }).collect(Collectors.toList());
        return new PageImpl<>(result);
    }
}
