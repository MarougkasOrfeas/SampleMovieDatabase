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
@Table(name = "MOVIE")
public class Movie extends BaseEntity{

    @Column(name = "TITLE")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genres genre;

    @Column(name = "PRODUCTION_BUDGET")
    private BigDecimal productionBudget;

    @Column(name = "RELEASE_DATE")
    private Integer releaseYear;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "ID")
    private Person director;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "MOVIE_ACTORS",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ACTOR_ID")
    )
    private List<Person> actors;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "MOVIE_PRODUCERS",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCER_ID")
    )
    private List<Person> producers;
}
