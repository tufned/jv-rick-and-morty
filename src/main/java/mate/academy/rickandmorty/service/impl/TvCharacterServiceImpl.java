package mate.academy.rickandmorty.service.impl;

import java.util.Optional;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import mate.academy.rickandmorty.mapper.TvCharacterMapper;
import mate.academy.rickandmorty.model.TvCharacter;
import mate.academy.rickandmorty.repository.TvCharacterRepository;
import mate.academy.rickandmorty.service.TvCharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TvCharacterServiceImpl implements TvCharacterService {
    private final TvCharacterRepository tvCharacterRepository;
    private final TvCharacterMapper tvCharacterMapper;

    @Override
    public Page<TvCharacterDto> getAll(String name, Pageable pageable) {
        Specification<TvCharacter> specification = Specification.where(null);
        if (name != null && !name.isBlank()) {
            specification = specification.and(
                    (root, query, cb) -> cb.like(cb.lower(root.get("name")),
                            "%" + name.toLowerCase() + "%"));
        }
        return tvCharacterRepository.findAll(specification, pageable).map(tvCharacterMapper::toDto);
    }

    @Transactional
    @Override
    public TvCharacterDto getRandom() {
        Optional<TvCharacter> randomTvCharacter = findRandomTvCharacter();
        while (randomTvCharacter.isEmpty()) {
            randomTvCharacter = findRandomTvCharacter();
        }
        return tvCharacterMapper.toDto(randomTvCharacter.get());
    }

    private Optional<TvCharacter> findRandomTvCharacter() {
        long randomId = new Random().nextLong(tvCharacterRepository.count()) + 1;
        return tvCharacterRepository.findById(randomId);
    }
}
