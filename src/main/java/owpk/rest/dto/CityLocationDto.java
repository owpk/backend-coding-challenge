package owpk.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Vorobyev Vyacheslav
 */
@JsonAutoDetect
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityLocationDto {
    private Float lon;
    private Float lat;
    private String name;
    @JsonIgnore
    private Float score;
}
