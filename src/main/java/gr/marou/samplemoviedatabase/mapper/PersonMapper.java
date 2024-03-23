package gr.marou.samplemoviedatabase.mapper;

import gr.marou.samplemoviedatabase.dto.PersonDTO;
import gr.marou.samplemoviedatabase.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper extends SMDBMapper<Person, PersonDTO> {

    @Mapping(target = "bestActorNominations", ignore = true)
    @Override
    PersonDTO toDto(Person person);

    @Mapping(target = "bestActorNominations", ignore = true)
    @Override
    Person toEntity(PersonDTO personDTO);
}
