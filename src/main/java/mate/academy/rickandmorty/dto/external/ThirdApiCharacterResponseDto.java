package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdApiCharacterResponseDto {
    @JsonProperty("id")
    private String externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;

}
