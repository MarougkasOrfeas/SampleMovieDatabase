package gr.marou.samplemoviedatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;

/**
 * An abstract repository interface providing common operations for repositories in the Sample Movie Database (SMDB) application.
 *
 * @param <T> the type of the entity managed by this repository
 * @param <I> the type of the entity's identifier
 */
@NoRepositoryBean
public interface AbstractRepository<T, I extends Serializable> extends JpaRepository<T, I> {

    /**
     * Soft deletes an entity by setting its 'deleted' flag to true.
     *
     * @param id the identifier of the entity to soft delete
     */
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = :id")
    void softDeleteById(@Param("id") Long id);
}
