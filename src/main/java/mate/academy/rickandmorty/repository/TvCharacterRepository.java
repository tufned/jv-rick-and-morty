package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.TvCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TvCharacterRepository
        extends JpaRepository<TvCharacter, Long>, JpaSpecificationExecutor<TvCharacter> {
}
