package gr.marou.samplemoviedatabase.dto;


import gr.marou.samplemoviedatabase.model.enums.Genres;
import gr.marou.samplemoviedatabase.model.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BestActorNominationDTO {
    private Long id;
    private Integer nominationYear;
    private Genres genre;
    private Result result;
    private PersonDTO actor;
}
