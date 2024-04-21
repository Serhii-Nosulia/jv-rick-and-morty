package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThirdApiCharacterResponseDto(@JsonProperty("id")
                                           String externalId,
                                           String name,
                                           String status,
                                           String species,
                                           String type,
                                           String gender
) {
}
