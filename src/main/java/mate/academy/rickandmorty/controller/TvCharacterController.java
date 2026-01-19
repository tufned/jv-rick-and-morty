package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.TvCharacterDto;
import mate.academy.rickandmorty.service.TvCharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class TvCharacterController {
    private final TvCharacterService tvCharacterService;

    @GetMapping()
    public List<TvCharacterDto> getAll(String name) {
        return tvCharacterService.getAll(name);
    }

    @GetMapping("/random")
    public TvCharacterDto getRandom() {
        return tvCharacterService.getRandom();
    }
}
