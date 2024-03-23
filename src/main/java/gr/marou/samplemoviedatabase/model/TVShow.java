package gr.marou.samplemoviedatabase.model;

import gr.marou.samplemoviedatabase.model.enums.Genres;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TV_SHOW")
public class TVShow extends BaseEntity{

    @Column(name = "TITLE")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genres genre;

    @Column(name = "MIN_BUDGET")
    private BigDecimal minProductionBudgetPerEpisode;

    @Column(name = "MAX_BUDGET")
    private BigDecimal maxProductionBudgetPerEpisode;

    @Column(name = "START_YEAR")
    private Integer startYear;

    @Column(name = "END_YEAR")
    private Integer endYear;

    @Column(name = "NUMBER_OF_EPISODES")
    private Integer numberOfEpisodes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "ID")
    private Person director;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TVSHOW_ACTORS",
            joinColumns = @JoinColumn(name = "TVSHOW_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID")
    )
    private List<Person> actors;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TVSHOW_PRODUCERS",
            joinColumns = @JoinColumn(name = "TVSHOW_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCER_ID")
    )
    private List<Person> producers;
}
