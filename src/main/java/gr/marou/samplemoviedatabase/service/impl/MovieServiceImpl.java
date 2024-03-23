package gr.marou.samplemoviedatabase.service.impl;

import gr.marou.samplemoviedatabase.dto.MovieDTO;
import gr.marou.samplemoviedatabase.exception.SMDBException;
import gr.marou.samplemoviedatabase.mapper.MovieMapper;
import gr.marou.samplemoviedatabase.mapper.SMDBMapper;
import gr.marou.samplemoviedatabase.model.Movie;
import gr.marou.samplemoviedatabase.repository.MovieRepository;
import gr.marou.samplemoviedatabase.service.AbstractService;
import gr.marou.samplemoviedatabase.service.MovieService;
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
public class MovieServiceImpl extends AbstractService<Movie, MovieDTO> implements MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieRepository movieRepository;
    private final SMDBMapper<Movie, MovieDTO> movieMapper;

    @Override
    public JpaRepository<Movie, Long> getRepository() {
        return movieRepository;
    }

    @Override
    public SMDBMapper<Movie, MovieDTO> getMapper() {
        return movieMapper;
    }

    @Override
    public void deleteById(Long id) {
        try{
            movieRepository.softDeleteById(id);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error occurred while deleting Movie with ID {}: {}", id, e.getMessage());
            throw new SMDBException("Error occurred while deleting Movie", true);
        }
    }
}
