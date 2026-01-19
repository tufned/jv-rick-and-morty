package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import mate.academy.rickandmorty.mapper.TvCharacterMapper;
import mate.academy.rickandmorty.model.TvCharacter;
import mate.academy.rickandmorty.repository.TvCharacterRepository;
import mate.academy.rickandmorty.service.TvCharacterService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TvCharacterServiceImpl implements TvCharacterService {
    private final TvCharacterRepository tvCharacterRepository;
    private final TvCharacterMapper tvCharacterMapper;

    @Override
    public List<TvCharacterDto> getAll(String name) {
        Specification<TvCharacter> specification = Specification.where(null);
        if (name != null && !name.isBlank()) {
            specification = specification.and(
                    (root, query, cb) -> cb.like(cb.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
        }
        return tvCharacterRepository.findAll(specification).stream().map(tvCharacterMapper::toDto)
                .toList();
    }

    @Override
    public TvCharacterDto getRandom() {
        TvCharacter random = tvCharacterRepository.findRandom();
        if (random == null) {
            throw new RuntimeException("Can't get character from DB");
        }
        return tvCharacterMapper.toDto(random);
    }
}
