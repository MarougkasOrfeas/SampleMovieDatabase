package gr.marou.samplemoviedatabase.controller;

import gr.marou.samplemoviedatabase.dto.TVShowDTO;
import gr.marou.samplemoviedatabase.service.TVShowService;
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
@RequestMapping("/tvShows")
@Tag(name = "TV Shows", description = "Endpoints for managing TV shows")
public class TVShowController {

    private static final Logger LOG = LoggerFactory.getLogger(TVShowController.class);

    private final TVShowService tvShowService;

    @Operation(summary = "Create a TV show")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTVShow(@RequestBody TVShowDTO tvShowDTO){
        LOG.info("Creating TV show: {}", tvShowDTO);
        tvShowService.create(tvShowDTO);
        LOG.info("TV show created successfully.");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all TV shows")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of TV shows retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No TV shows found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TVShowDTO>> getAllTVShows() {
        LOG.info("Fetching all TV shows.");
        List<TVShowDTO> tvShows = tvShowService.read();
        LOG.info("Fetched {} TV shows.", tvShows.size());
        return ResponseEntity.ok(tvShows);
    }

    @Operation(summary = "Get a TV show by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TV show retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "TV show not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TVShowDTO> getTVShowById(@PathVariable Long id) {
        LOG.info("Fetching TV show with ID: {}", id);
        TVShowDTO tvShow = tvShowService.read(id);
        if (tvShow != null) {
            LOG.info("TV show found with ID: {}", id);
            return ResponseEntity.ok(tvShow);
        } else {
            LOG.warn("TV show not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a TV show")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TV Show updated successfully"),
            @ApiResponse(responseCode = "404", description = "TV Show not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateTVShow(@PathVariable Long id, @RequestBody TVShowDTO tvShowDTO) {
        LOG.info("Updating TV show with ID: {}", id);
        TVShowDTO existingTVShow = tvShowService.read(id);
        if (existingTVShow != null) {
            tvShowService.update(tvShowDTO);
            LOG.info("TV show updated successfully with ID: {}", id);
            return ResponseEntity.ok().build();
        } else {
            LOG.warn("TV show not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a TV show")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TV show deleted successfully"),
            @ApiResponse(responseCode = "404", description = "TV show not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTVShow(@PathVariable Long id) {
        LOG.info("Soft deleting TV show with ID: {}", id);
        tvShowService.deleteById(id);
        LOG.info("TV show deleted successfully with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
