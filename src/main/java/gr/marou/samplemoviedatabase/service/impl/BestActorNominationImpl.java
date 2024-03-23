package gr.marou.samplemoviedatabase.service.impl;

import gr.marou.samplemoviedatabase.dto.BestActorNominationDTO;
import gr.marou.samplemoviedatabase.exception.SMDBException;
import gr.marou.samplemoviedatabase.mapper.SMDBMapper;
import gr.marou.samplemoviedatabase.model.BestActorNomination;
import gr.marou.samplemoviedatabase.repository.BestActorNominationRepository;
import gr.marou.samplemoviedatabase.service.AbstractService;
import gr.marou.samplemoviedatabase.service.BestActorNominationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BestActorNominationImpl extends AbstractService<BestActorNomination, BestActorNominationDTO>
        implements BestActorNominationService {

    private static final Logger LOG = LoggerFactory.getLogger(BestActorNominationImpl.class);

    private final BestActorNominationRepository bestActorNominationRepository;
    private final SMDBMapper<BestActorNomination, BestActorNominationDTO> bestActorNominationMapper;

    @Override
    public JpaRepository<BestActorNomination, Long> getRepository() {
        return bestActorNominationRepository;
    }

    @Override
    public SMDBMapper<BestActorNomination, BestActorNominationDTO> getMapper() {
        return bestActorNominationMapper;
    }

    @Override
    public void deleteById(Long id) {
        try {
            bestActorNominationRepository.softDeleteById(id);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error occurred while deleting BestActorNomination with ID {}: {}", id, e.getMessage());
            throw new SMDBException("Error occurred while deleting BestActorNomination", true);
        }
    }

    @Override
    public List<BestActorNominationDTO> findByYearBetweenOrderByRatingDesc(Integer startYear, Integer endYear) {
        try {
            LOG.info("Finding best actor nominations between years {} and {} ordered by rating descending", startYear, endYear);
            List<BestActorNomination> nominations =  bestActorNominationRepository.findWinnersByYearRangeOrderedByRatingDesc(startYear, endYear);

            List<BestActorNominationDTO> nominationDTOs = new ArrayList<>();
            for (BestActorNomination nomination : nominations) {
                BestActorNominationDTO dto = bestActorNominationMapper.toDto(nomination);
                nominationDTOs.add(dto);
            }
            return nominationDTOs;
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error occurred while finding BestActorNominations between years {} and {}: {}", startYear, endYear, e.getMessage());
            throw new SMDBException("Error occurred while finding BestActorNominations", true);
        }
    }
}
