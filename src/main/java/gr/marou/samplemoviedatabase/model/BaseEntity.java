package gr.marou.samplemoviedatabase.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import java.io.Serializable;


@OptimisticLocking(type = OptimisticLockType.VERSION)
@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DELETED")
    private boolean deleted;
}
