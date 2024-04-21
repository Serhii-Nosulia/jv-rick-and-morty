package mate.academy.rickandmorty.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String URI_BASE = "https://rickandmortyapi.com/api/character";

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    @PostConstruct
    public void loadDataToDb() {
        String page = URI_BASE;
        while (page != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(page))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());
                CharacterResponseDataDto characterResponseDataDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDataDto.class);
                List<Character> characterList = characterResponseDataDto.results().stream()
                        .map(characterMapper::toCharacter)
                        .toList();
                characterRepository.saveAll(characterList);
                page = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get data from API", e);
            }
        }
    }
}
