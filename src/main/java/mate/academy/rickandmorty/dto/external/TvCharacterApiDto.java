package mate.academy.rickandmorty.dto.external;

import java.util.List;

public record TvCharacterApiDto(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        UrlNameDto origin,
        UrlNameDto location,
        String image,
        List<String> episode,
        String url,
        String created
) {
    public record UrlNameDto(
            String name,
            String url
    ) {
    }
}
