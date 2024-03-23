package gr.marou.samplemoviedatabase.controller;

import gr.marou.samplemoviedatabase.dto.MovieDTO;
import gr.marou.samplemoviedatabase.service.AbstractService;
import gr.marou.samplemoviedatabase.service.MovieService;
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
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Endpoints for managing movies")
public class MovieController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractService.class);

    private final MovieService movieService;

    @Operation(summary = "Create a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody MovieDTO movieDTO){
        LOG.info("Creating movie: {}", movieDTO);
        movieService.create(movieDTO);
        LOG.info("Movie created successfully.");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of movies retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No movies found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        LOG.info("Fetching all movies.");
        List<MovieDTO> movies = movieService.read();
        LOG.info("Fetched {} movies.", movies.size());
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Get a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        LOG.info("Fetching movie with ID: {}", id);
        MovieDTO movie = movieService.read(id);
        if (movie != null) {
            LOG.info("Movie found with ID: {}", id);
            return ResponseEntity.ok(movie);
        } else {
            LOG.warn("Movie not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        LOG.info("Updating movie with ID: {}", id);
        MovieDTO existingMovie = movieService.read(id);
        if (existingMovie != null) {
            movieService.update(movieDTO);
            LOG.info("Movie updated successfully with ID: {}", id);
            return ResponseEntity.ok().build();
        } else {
            LOG.warn("Movie not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id) {
        LOG.info("Soft deleting movie with ID: {}", id);
        movieService.deleteById(id);
        LOG.info("Movie deleted successfully with ID: {}", id);
        return ResponseEntity.ok().build();
    }
}
