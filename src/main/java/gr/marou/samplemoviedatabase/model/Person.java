package gr.marou.samplemoviedatabase.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gr.marou.samplemoviedatabase.model.enums.Professions;
import gr.marou.samplemoviedatabase.model.enums.SalaryType;
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
@Table(name = "PERSON")
public class Person extends BaseEntity{

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROFESSIONS")
    private Professions professions;

    @Column(name = "SALARY")
    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALARY_TYPE")
    private SalaryType salaryType;

    @OneToMany(mappedBy = "actor")
//    @JsonManagedReference
    private List<BestActorNomination> bestActorNominations;
}
