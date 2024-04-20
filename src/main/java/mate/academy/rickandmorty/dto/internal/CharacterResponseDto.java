package mate.academy.rickandmorty.dto.internal;

public record CharacterResponseDto(Long id,
                                   String externalId,
                                   String name,
                                   String status,
                                   String gender
) {
}
