package gr.marou.samplemoviedatabase.controller;

import gr.marou.samplemoviedatabase.dto.BestActorNominationDTO;
import gr.marou.samplemoviedatabase.service.BestActorNominationService;
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
@RequestMapping("/nominations")
@Tag(name = "Best Actor Nominations", description = "Endpoints for managing Best Actor nominations")
public class BestActorNominationController {
    private static final Logger LOG = LoggerFactory.getLogger(BestActorNominationController.class);

    private final BestActorNominationService nominationService;

    @Operation(summary = "Create a new Best Actor Nomination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNomination(@RequestBody BestActorNominationDTO nominationDTO){
        LOG.info("Creating best actor nomination: {}", nominationDTO);
        nominationService.create(nominationDTO);
        LOG.info("Best actor nomination created successfully.");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Retrieve all Best Actor Nominations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of best actor nominations retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No nominations found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BestActorNominationDTO>> getAllNominations() {
        LOG.info("Fetching all best actor nominations.");
        List<BestActorNominationDTO> nominations = nominationService.read();
        LOG.info("Fetched {} best actor nominations.", nominations.size());
        return ResponseEntity.ok(nominations);
    }

    @Operation(summary = "Retrieve a specific Best Actor Nomination by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Best actor nomination retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Nomination not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BestActorNominationDTO> getNominationById(@PathVariable Long id) {
        LOG.info("Fetching best actor nomination with ID: {}", id);
        BestActorNominationDTO nomination = nominationService.read(id);
        if (nomination != null) {
            LOG.info("Best actor nomination found with ID: {}", id);
            return ResponseEntity.ok(nomination);
        } else {
            LOG.warn("Best actor nomination not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Retrieve all Best Actor Nominations in a specific range of years")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Best actor nominations retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/range")
    public List<BestActorNominationDTO> getBestActorNominationsByYearRange(
            @RequestParam("startYear") Integer startYear,
            @RequestParam("endYear") Integer endYear) {
        return nominationService.findByYearBetweenOrderByRatingDesc(startYear, endYear);
    }


    @Operation(summary = "Update a Best Actor Nomination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Best actor nomination updated successfully"),
            @ApiResponse(responseCode = "404", description = "Nomination not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateNomination(@PathVariable Long id, @RequestBody BestActorNominationDTO nominationDTO) {
        LOG.info("Updating best actor nomination with ID: {}", id);
        BestActorNominationDTO existingNomination = nominationService.read(id);
        if (existingNomination != null) {
            nominationService.update(nominationDTO);
            LOG.info("Best actor nomination updated successfully with ID: {}", id);
            return ResponseEntity.ok().build();
        } else {
            LOG.warn("Best actor nomination not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a Best Actor Nomination", description = "Updates the deleted attribute to true.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Best actor nomination deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Nomination not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteNomination(@PathVariable Long id) {
        LOG.info("Soft deleting best actor nomination with ID: {}", id);
        nominationService.deleteById(id);
        LOG.info("Best actor nomination soft deleted successfully with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
