package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.ThirdApiCharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterResponseDto toDto(Character character);

    Character toCharacter(ThirdApiCharacterResponseDto thirdApiCharacterResponseDto);
}
