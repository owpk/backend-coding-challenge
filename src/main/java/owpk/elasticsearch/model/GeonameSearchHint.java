package owpk.elasticsearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;
import owpk.elasticsearch.dto.ElasticsearchGeoname;

import static org.springframework.data.elasticsearch.annotations.FieldType.*;

import java.lang.Double;

/**
 * @author Vorobyev Vyacheslav
 */
@Document(indexName = "geo")
@Getter
@Setter
@ToString
public class GeonameSearchHint {

    @Id
    private String id;

    @MultiField(
            mainField = @Field(type = Text, fielddata = true),
            otherFields = {@InnerField(suffix = "vr", type = Keyword)}
    )
    private String title;

    private Float score;

    @Field(type = Nested, includeInParent = true)
    private ElasticsearchGeoname geoname;

    @Field(type = Keyword)
    private String[] tags;
}