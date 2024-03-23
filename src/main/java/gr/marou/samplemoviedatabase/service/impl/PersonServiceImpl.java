package gr.marou.samplemoviedatabase.service.impl;

import gr.marou.samplemoviedatabase.dto.PersonDTO;
import gr.marou.samplemoviedatabase.exception.SMDBException;
import gr.marou.samplemoviedatabase.mapper.PersonMapper;
import gr.marou.samplemoviedatabase.mapper.SMDBMapper;
import gr.marou.samplemoviedatabase.model.Person;
import gr.marou.samplemoviedatabase.repository.PersonRepository;
import gr.marou.samplemoviedatabase.service.AbstractService;
import gr.marou.samplemoviedatabase.service.PersonService;
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
public class PersonServiceImpl extends AbstractService<Person, PersonDTO> implements PersonService {

    private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;
    private final SMDBMapper<Person, PersonDTO> personMapper;

    @Override
    public JpaRepository<Person, Long> getRepository() {
        return personRepository;
    }

    @Override
    public SMDBMapper<Person, PersonDTO> getMapper() {
        return personMapper;
    }

    @Override
    public void deleteById(Long id) {
        try{
            personRepository.softDeleteById(id);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Error occurred while deleting Person with ID {}: {}", id, e.getMessage());
            throw new SMDBException("Error occurred while deleting Person", true);
        }
    }
}
