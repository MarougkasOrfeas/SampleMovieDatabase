package gr.marou.samplemoviedatabase.service.impl;

import gr.marou.samplemoviedatabase.exception.SMDBException;
import gr.marou.samplemoviedatabase.model.Movie;
import gr.marou.samplemoviedatabase.model.Person;
import gr.marou.samplemoviedatabase.model.TVShow;
import gr.marou.samplemoviedatabase.repository.MovieRepository;
import gr.marou.samplemoviedatabase.repository.TVShowRepository;
import gr.marou.samplemoviedatabase.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    private final MovieRepository movieRepository;
    private final TVShowRepository tvShowRepository;


    /**
     * Calculates the total cost of a movie based on the salaries of its actors, producers, and director.
     *
     * @param id The ID of the movie for which the total cost is to be calculated.
     * @return The total cost of the movie.
     * @throws SMDBException if no movie is found with the specified ID.
     *
     */
    @Override
    public BigDecimal getMovieTotalCost(Long id) {
        LOG.info("Calculating total cost for movie with ID: {}", id);
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new SMDBException("Movie with ID " + id + " not found", true));
        BigDecimal totalCostForMovies  = BigDecimal.ZERO;
        for (Person actors : movie.getActors()){
            totalCostForMovies  = totalCostForMovies .add(actors.getSalary());
        }
        for(Person producers : movie.getProducers()){
            totalCostForMovies  = totalCostForMovies .add(producers.getSalary());
        }
        totalCostForMovies  = totalCostForMovies .add(movie.getDirector().getSalary());
        LOG.info("Total cost calculated for movie with ID {}: {}", id, totalCostForMovies);
        return totalCostForMovies ;
    }

    /**
     * Calculates the total cost of a TV show based on the salaries of its actors, producers, and director.
     *
     * @param id The ID of the TV show for which the total cost is to be calculated.
     * @return The total cost of the TV show.
     * @throws SMDBException if no TV show is found with the specified ID.
     */
    @Override
    public BigDecimal getTVShowTotalCost(Long id) {
        LOG.info("Calculating total cost for TV show with ID: {}", id);
        TVShow tvShow = tvShowRepository.findById(id)
                .orElseThrow(() -> new SMDBException("TV show with ID " + id + " not found", true));
        BigDecimal totalCostForTvShows  = BigDecimal.ZERO;
        for (Person actor : tvShow.getActors()) {
            totalCostForTvShows = totalCostForTvShows.add(actor.getSalary().multiply(tvShow.getMinProductionBudgetPerEpisode()));
        }
        for (Person producer : tvShow.getProducers()) {
            totalCostForTvShows = totalCostForTvShows.add(producer.getSalary().multiply(tvShow.getMaxProductionBudgetPerEpisode()));
        }
        totalCostForTvShows = totalCostForTvShows.add(tvShow.getDirector().getSalary()).multiply(tvShow.getMaxProductionBudgetPerEpisode());
        LOG.info("Total cost calculated for TV show with ID {}: {}", id, totalCostForTvShows);
        return totalCostForTvShows;
    }

}
