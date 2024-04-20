package mate.academy.rickandmorty.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
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
                characterResponseDataDto.results().stream()
                        .map(characterMapper::toCharacter)
                        .forEach(characterRepository::save);
                page = characterResponseDataDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Can't get data from API", e);
            }
        }
    }
}
