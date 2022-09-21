package owpk.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import owpk.elasticsearch.dto.ElasticsearchGeoname;
import owpk.elasticsearch.model.GeonameSearchHint;
import owpk.elasticsearch.repos.GeonamesESRepository;
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

                    var geoname = new ElasticsearchGeoname();
                    geoname.setLat(x.getLat().floatValue());
                    geoname.setLon(x.getLong().floatValue());
                    geoname.setName(x.getName());

                    var article = new GeonameSearchHint();
                    article.setTitle(x.getCountry());
                    article.setTags(new String[]{"city"});
                    article.setGeoname(geoname);
                    article.setId(x.getId().toPlainString());
                    return article;
                }).collect(Collectors.toList());
        geonamesESRepository.saveAll(cities);
    }
}
