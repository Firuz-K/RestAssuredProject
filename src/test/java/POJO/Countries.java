package POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Countries {

    private String country_id;
    private String country_name;
    private int region_id;


}
