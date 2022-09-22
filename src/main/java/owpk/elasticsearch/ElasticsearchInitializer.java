package owpk.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;
import owpk.elasticsearch.model.ElasticsearchGeoname;
import owpk.elasticsearch.repos.SpringDataGeonamesESRepository;
import owpk.jooqrepo.GeonameJooqRepository;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

/**
 * @author Vorobyev Vyacheslav
 */
@Service
@AllArgsConstructor
@Slf4j
public class ElasticsearchInitializer {
    private final GeonameJooqRepository cityRepository;
    private final SpringDataGeonamesESRepository geonamesESRepository;

    @PostConstruct
    public void initElasticsearch() {
        var cities = cityRepository.findAll()
                .stream()
                .map(x -> {
                    var article = new ElasticsearchGeoname();
                    article.setName(x.getName());
                    article.setId(x.getId().toPlainString());
                    article.setLocation(new GeoPoint(x.getLat().doubleValue(),
                            x.getLong().doubleValue()));
                    article.setLon(x.getLong().floatValue());
                    article.setLat(x.getLat().floatValue());
                    return article;
                }).collect(Collectors.toList());
        geonamesESRepository.saveAll(cities);
    }
}