package gr.marou.samplemoviedatabase.dto;

import gr.marou.samplemoviedatabase.model.enums.Genres;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private Genres genre;
    private BigDecimal productionBudget;
    private Integer releaseYear;
    private PersonDTO director;
    private List<PersonDTO> actors;
    private List<PersonDTO> producers;
}
