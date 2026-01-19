package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;

public interface TvCharacterService {
    List<TvCharacterDto> getAll(String name);

    TvCharacterDto getRandom();
}
