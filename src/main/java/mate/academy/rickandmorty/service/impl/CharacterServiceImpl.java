package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.exception.DataNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random;
    private final RickAndMortyClient rickAndMortyClient;

    @PostConstruct
    public void loadDataToDb() {
        rickAndMortyClient.loadDataToDb();
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        long count = characterRepository.count();
        Character character = characterRepository.findById(random.nextLong(count))
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
