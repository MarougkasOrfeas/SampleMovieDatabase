package gr.marou.samplemoviedatabase.controller;

import gr.marou.samplemoviedatabase.dto.PersonDTO;
import gr.marou.samplemoviedatabase.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/persons")
@Tag(name = "Persons", description = "Endpoints for managing persons")
public class PersonController {

    private static final Logger LOG = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    @Operation(summary = "Create a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPerson(@RequestBody PersonDTO personDTO){
        LOG.info("Creating person: {}", personDTO);
        personService.create(personDTO);
        LOG.info("Person created successfully.");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of persons retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No persons found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        LOG.info("Fetching all persons.");
        List<PersonDTO> persons = personService.read();
        LOG.info("Fetched {} persons.", persons.size());
        return ResponseEntity.ok(persons);
    }

    @Operation(summary = "Get a person by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        LOG.info("Fetching person with ID: {}", id);
        PersonDTO person = personService.read(id);
        if (person != null) {
            LOG.info("Person found with ID: {}", id);
            return ResponseEntity.ok(person);
        } else {
            LOG.warn("Person not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        LOG.info("Updating person with ID: {}", id);
        PersonDTO existingPerson = personService.read(id);
        if (existingPerson != null) {
            personService.update(personDTO);
            LOG.info("Person updated successfully with ID: {}", id);
            return ResponseEntity.ok().build();
        } else {
            LOG.warn("Person not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id) {
        LOG.info("Deleting person with ID: {}", id);
        personService.deleteById(id);
        LOG.info("Person deleted successfully with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
