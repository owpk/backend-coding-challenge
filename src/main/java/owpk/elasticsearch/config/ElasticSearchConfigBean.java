package owpk.elasticsearch.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Vorobyev Vyacheslav
 */
@Component
@Getter
public class ElasticSearchConfigBean {

    @Value("${elasticsearch.host}")
    private String host;
}
