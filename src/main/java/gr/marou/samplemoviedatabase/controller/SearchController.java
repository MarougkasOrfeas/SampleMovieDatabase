package gr.marou.samplemoviedatabase.controller;

import gr.marou.samplemoviedatabase.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/search")
@Tag(name = "Search", description = "Endpoints for searching movies and TV shows")
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "Get total cost of a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total cost of the movie"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<BigDecimal> getMovieTotalCost(@PathVariable(value = "id") Long id) {
        BigDecimal totalCost = searchService.getMovieTotalCost(id);
        if (totalCost != null) {
            return ResponseEntity.ok(totalCost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get total cost of TV shows")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total cost of the TV show"),
            @ApiResponse(responseCode = "404", description = "TV show not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/tvShows/{id}")
    public ResponseEntity<BigDecimal> getTvShowsTotalCost(@PathVariable(value = "id") Long id) {
        BigDecimal totalCost = searchService.getTVShowTotalCost(id);
        if (totalCost != null) {
            return ResponseEntity.ok(totalCost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
