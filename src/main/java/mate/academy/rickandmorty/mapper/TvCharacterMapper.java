package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.TvCharacterApiDto;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import mate.academy.rickandmorty.model.TvCharacter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TvCharacterMapper {
    @Mapping(source = "id", target = "externalId")
    @Mapping(target = "episodes", expression = "java(String.join(\",\", apiDto.episode()))")
    TvCharacter toModel(TvCharacterApiDto apiDto);

    TvCharacterDto toDto(TvCharacter model);
}
