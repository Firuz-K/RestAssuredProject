package POJO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BreakingBad {

    private int char_id;
    private String name;
    private String birthday;
    private String[] occupation;
    private String img;
    private String status;
    private String nickname;
    private String[] appearance;
    private String portrayed;
    private String category;
}
