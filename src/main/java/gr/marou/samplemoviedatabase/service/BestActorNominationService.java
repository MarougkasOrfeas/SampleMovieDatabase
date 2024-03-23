package gr.marou.samplemoviedatabase.service;

import gr.marou.samplemoviedatabase.dto.BestActorNominationDTO;
import gr.marou.samplemoviedatabase.model.BestActorNomination;

import java.util.List;

public interface BestActorNominationService extends BaseService<BestActorNomination, BestActorNominationDTO, Long> {

    List<BestActorNominationDTO> findByYearBetweenOrderByRatingDesc(Integer startYear, Integer endYear);
}
