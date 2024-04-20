package mate.academy.rickandmorty.dto.external;

public record CharacterResponseMetaDataDto(Integer count,
                                           Integer pages,
                                           String next,
                                           String prev
) {
}
