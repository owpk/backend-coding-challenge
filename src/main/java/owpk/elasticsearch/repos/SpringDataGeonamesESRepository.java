package owpk.elasticsearch.repos;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import owpk.elasticsearch.model.ElasticsearchGeoname;

public interface SpringDataGeonamesESRepository extends ElasticsearchRepository<ElasticsearchGeoname, Long>, GeonamesESRepository {

}
