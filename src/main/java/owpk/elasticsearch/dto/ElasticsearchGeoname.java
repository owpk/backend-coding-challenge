package owpk.elasticsearch.dto;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Vorobyev Vyacheslav
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ElasticsearchGeoname {

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Float)
    private Float lat;

    @Field(type = FieldType.Float)
    private Float lon;
}
