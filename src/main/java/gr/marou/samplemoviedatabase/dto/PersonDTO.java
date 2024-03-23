package gr.marou.samplemoviedatabase.dto;

import gr.marou.samplemoviedatabase.model.enums.Professions;
import gr.marou.samplemoviedatabase.model.enums.SalaryType;
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
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Professions professions;
    private BigDecimal salary;
    private SalaryType salaryType;
    private List<BestActorNominationDTO> bestActorNominations;
}
