package gr.marou.samplemoviedatabase.service;

import gr.marou.samplemoviedatabase.model.Movie;

import java.math.BigDecimal;
import java.util.List;

public interface SearchService {

    BigDecimal getMovieTotalCost(Long id);

    BigDecimal getTVShowTotalCost(Long id);

}
