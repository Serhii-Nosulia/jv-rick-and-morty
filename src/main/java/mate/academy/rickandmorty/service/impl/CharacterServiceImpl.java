package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.exception.DataNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Character character = characterRepository.getRandomCharacter()
                .orElseThrow(
                        () -> new DataNotFoundException("Couldn't find data")
                );
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterResponseDto> findAllByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
