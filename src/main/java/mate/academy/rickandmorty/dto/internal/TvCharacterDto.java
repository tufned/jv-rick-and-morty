package mate.academy.rickandmorty.dto.internal;

import mate.academy.rickandmorty.model.TvCharacter;

public record TvCharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        TvCharacter.Gender gender
) {
}
