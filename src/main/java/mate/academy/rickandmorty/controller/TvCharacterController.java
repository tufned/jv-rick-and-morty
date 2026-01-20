package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import mate.academy.rickandmorty.service.TvCharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class TvCharacterController {
    private final TvCharacterService tvCharacterService;

    @GetMapping()
    @Operation(summary = "Get all characters", description = """
            Returns all characters wrapped in Pageable.
            Can be used with 'name' search param to get all characters with found name.""")
    public Page<TvCharacterDto> getAll(
            @Parameter(description = "Used to search by name", example = "Rick") String name,
            Pageable pageable) {
        return tvCharacterService.getAll(name, pageable);
    }

    @Operation(summary = "Get random character", description = """
            Returns single random character from all characters.""")
    @GetMapping("/random")
    public TvCharacterDto getRandom() {
        return tvCharacterService.getRandom();
    }
}
