package gr.marou.samplemoviedatabase.mapper;

import gr.marou.samplemoviedatabase.dto.MovieDTO;
import gr.marou.samplemoviedatabase.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper extends SMDBMapper<Movie, MovieDTO> {

    @Override
    MovieDTO toDto(Movie movie);

    @Override
    Movie toEntity(MovieDTO movieDTO);
}
