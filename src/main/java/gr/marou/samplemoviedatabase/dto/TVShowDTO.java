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
public class TVShowDTO {
    private Long id;
    private String title;
    private Genres genre;
    private BigDecimal minProductionBudgetPerEpisode;
    private BigDecimal maxProductionBudgetPerEpisode;
    private Integer startYear;
    private Integer endYear;
    private Integer numberOfEpisodes;
    private PersonDTO director;
    private List<PersonDTO> actors;
    private List<PersonDTO> producers;
}
