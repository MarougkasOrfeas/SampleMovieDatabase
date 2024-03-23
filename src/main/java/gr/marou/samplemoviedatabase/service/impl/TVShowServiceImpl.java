package gr.marou.samplemoviedatabase.service.impl;

import gr.marou.samplemoviedatabase.dto.TVShowDTO;
import gr.marou.samplemoviedatabase.exception.SMDBException;
import gr.marou.samplemoviedatabase.mapper.SMDBMapper;
import gr.marou.samplemoviedatabase.model.TVShow;
import gr.marou.samplemoviedatabase.repository.TVShowRepository;
import gr.marou.samplemoviedatabase.service.AbstractService;
import gr.marou.samplemoviedatabase.service.TVShowService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class TVShowServiceImpl extends AbstractService<TVShow, TVShowDTO> implements TVShowService {

    private static final Logger LOG = LoggerFactory.getLogger(TVShowServiceImpl.class);

    private final TVShowRepository tvShowRepository;
    private final SMDBMapper<TVShow, TVShowDTO> tvShowMapper;

    @Override
    public JpaRepository<TVShow, Long> getRepository() {
        return tvShowRepository;
    }

    @Override
    public SMDBMapper<TVShow, TVShowDTO> getMapper() {
        return tvShowMapper;
    }

    @Override
    public void deleteById(Long id) {
        try{
            tvShowRepository.softDeleteById(id);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error occurred while deleting TvShow with ID {}: {}", id, e.getMessage());
            throw new SMDBException("Error occurred while deleting TvShow", true);
        }
    }
}
