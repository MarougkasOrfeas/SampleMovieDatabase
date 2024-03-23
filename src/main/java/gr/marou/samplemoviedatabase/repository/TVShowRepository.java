package gr.marou.samplemoviedatabase.repository;

import gr.marou.samplemoviedatabase.model.TVShow;
import org.springframework.stereotype.Repository;

@Repository
public interface TVShowRepository extends AbstractRepository<TVShow, Long> {
}
