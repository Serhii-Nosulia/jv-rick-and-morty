package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rick and Morty universe",
        description = "Endpoints for managing Rick and Morty universe characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    @Operation(summary = "Get the random character",
            description = "Get the random character")
    public CharacterResponseDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping()
    @Operation(summary = "Find the character by name",
            description = "Find the character by name")
    public List<CharacterResponseDto> searchCharactersByName(@RequestParam String name) {
        return characterService.findAllByName(name);
    }
}