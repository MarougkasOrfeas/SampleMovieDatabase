package gr.marou.samplemoviedatabase.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import gr.marou.samplemoviedatabase.model.enums.Genres;
import gr.marou.samplemoviedatabase.model.enums.Result;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BEST_ACTOR_NOMINATION")
public class BestActorNomination extends BaseEntity{

    @Column(name = "NOMINATION_YEAR")
    private Integer nominationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genres genre;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT")
    private Result result;

    @JoinColumn(name = "ACTOR_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JsonBackReference
    private Person actor;

}
