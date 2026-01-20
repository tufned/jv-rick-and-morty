package mate.academy.rickandmorty.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "characters")
@Getter
@Setter
public class TvCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "external_id", unique = true, nullable = false)
    private Long externalId;

    @Schema(example = "Rick Sanchez")
    @NotNull
    private String name;

    @Schema(example = "Alive")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private Status status;

    @Schema(example = "Human")
    private String species;

    @Schema(example = "Superhuman (Ghost trains summoner)")
    private String type;

    @Schema(example = "Male")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255)")
    private Gender gender;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "origin_name")),
            @AttributeOverride(name = "url", column = @Column(name = "origin_url"))
    })
    private Origin origin;

    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "location_name")),
            @AttributeOverride(name = "url", column = @Column(name = "location_url"))
    })
    private Location location;

    @Schema(example = "https://rickandmortyapi.com/api/character/avatar/1.jpeg")
    private String image;

    @Column(length = 10000)
    private String episodes;

    @Schema(example = "https://rickandmortyapi.com/api/character/1")
    private String url;

    @Schema(example = "2017-11-04T18:48:46.250Z")
    private ZonedDateTime created;

    public enum Status {
        Alive, Dead, unknown
    }

    public enum Gender {
        Male, Female, Genderless, unknown
    }
}
