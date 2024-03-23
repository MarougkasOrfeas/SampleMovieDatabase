package gr.marou.samplemoviedatabase.repository;

import gr.marou.samplemoviedatabase.model.BestActorNomination;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestActorNominationRepository extends AbstractRepository<BestActorNomination, Long> {

    @Query("SELECT b FROM BestActorNomination b WHERE b.nominationYear BETWEEN :startYear AND :endYear AND b.result = 'WON' ORDER BY b.result DESC")
    List<BestActorNomination> findWinnersByYearRangeOrderedByRatingDesc(@Param("startYear") Integer startYear, @Param("endYear") Integer endYear);
}
