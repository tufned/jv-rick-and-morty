package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import mate.academy.rickandmorty.dto.external.TvCharacterApiDto;
import mate.academy.rickandmorty.mapper.TvCharacterMapper;
import mate.academy.rickandmorty.repository.TvCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ReferenceDataService {
    private final ObjectMapper objectMapper;
    private final TvCharacterRepository tvCharacterRepository;
    private final TvCharacterMapper tvCharacterMapper;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    @Value("${mate.academy.api.rickAndMortyBaseUrl}")
    private String referenceApiBaseUrl;

    @Autowired
    public ReferenceDataService(ObjectMapper objectMapper,
                                TvCharacterRepository tvCharacterRepository,
                                TvCharacterMapper tvCharacterMapper) {
        this.objectMapper = objectMapper;
        this.tvCharacterRepository = tvCharacterRepository;
        this.tvCharacterMapper = tvCharacterMapper;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fetchReferenceCharacters() {
        URI uri = URI.create(referenceApiBaseUrl + "/character");
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonNode = objectMapper.readTree(response.body());
            JsonNode resultsField = jsonNode.get("results");

            if (resultsField == null || !resultsField.isArray()) {
                throw new RuntimeException();
            }

            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, TvCharacterApiDto.class);
            List<TvCharacterApiDto> responseDtos =
                    objectMapper.convertValue(resultsField, listType);

            tvCharacterRepository.saveAll(
                    responseDtos.stream().map(tvCharacterMapper::toModel).toList());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't fetch data from external API", e);
        }
    }
}
