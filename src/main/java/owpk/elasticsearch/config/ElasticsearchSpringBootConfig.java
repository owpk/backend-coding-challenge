package owpk.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.DefaultElasticsearchTypeMapper;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "owpk.elasticsearch.repos")
public class ElasticsearchSpringBootConfig {

    @Bean
    public RestHighLevelClient client(ElasticSearchConfigBean configBean) {
        var clientConfiguration =
                ClientConfiguration.builder()
                        .connectedTo(configBean.getHost())
                        .build();
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(ElasticSearchConfigBean cfg) {
        return new ElasticsearchRestTemplate(client(cfg));
    }
}