package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record CharacterResponseDataDto(CharacterResponseMetaDataDto info,
                                       List<ThirdApiCharacterResponseDto> results) {
}
