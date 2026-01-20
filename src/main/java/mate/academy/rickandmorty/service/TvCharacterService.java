package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TvCharacterService {
    Page<TvCharacterDto> getAll(String name, Pageable pageable);

    TvCharacterDto getRandom();
}
