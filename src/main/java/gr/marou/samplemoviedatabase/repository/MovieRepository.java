package gr.marou.samplemoviedatabase.repository;

import gr.marou.samplemoviedatabase.model.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends AbstractRepository<Movie, Long>{
}
