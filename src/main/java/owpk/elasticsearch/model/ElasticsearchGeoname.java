package owpk.elasticsearch.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.Id;

/**
 * @author Vorobyev Vyacheslav
 */
@Document(indexName = "geo")
@Getter
@Setter
@ToString
public class ElasticsearchGeoname {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String country;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Float)
    private Float lat;

    @Field(type = FieldType.Float)
    private Float lon;

}
