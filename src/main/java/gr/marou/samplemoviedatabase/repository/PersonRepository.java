package gr.marou.samplemoviedatabase.repository;

import gr.marou.samplemoviedatabase.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends AbstractRepository<Person, Long> {

}
