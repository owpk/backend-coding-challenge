package owpk.domain;

import lombok.*;

/**
 * @author Vorobyev Vyacheslav
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityLocation {
    private String name;
    private Float score;
    private Float lat;
    private Float lon;
}