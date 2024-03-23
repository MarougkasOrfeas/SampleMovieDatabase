package gr.marou.samplemoviedatabase.mapper;

import gr.marou.samplemoviedatabase.dto.BestActorNominationDTO;
import gr.marou.samplemoviedatabase.model.BestActorNomination;
import gr.marou.samplemoviedatabase.model.Person;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface BestActorNominationMapper extends SMDBMapper<BestActorNomination, BestActorNominationDTO>{

    @Mapping(target = "actor", source = "actor")
    @Override
    BestActorNominationDTO toDto(BestActorNomination bestActorNomination);

    @Mapping(target = "actor", source = "actor")
    @Override
    BestActorNomination toEntity(BestActorNominationDTO bestActorNominationDTO);
}
