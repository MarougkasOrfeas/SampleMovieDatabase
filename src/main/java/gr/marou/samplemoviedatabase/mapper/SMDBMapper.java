package gr.marou.samplemoviedatabase.mapper;

import org.mapstruct.InheritConfiguration;

import java.util.Collection;
import java.util.List;

/**
 * Generic interface representing a mapper between entity and DTO in the Sample Movie Database (SMDB) application.
 *
 * @param <ENTITY> the type of the entity
 * @param <DTO>    the type of the DTO
 */
public interface SMDBMapper<ENTITY, DTO>{

    @InheritConfiguration
    DTO toDto(ENTITY entity);

    @InheritConfiguration
    ENTITY toEntity(DTO dto);

}
