package gr.marou.samplemoviedatabase.mapper;

import gr.marou.samplemoviedatabase.dto.TVShowDTO;
import gr.marou.samplemoviedatabase.model.TVShow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TVShowMapper extends SMDBMapper<TVShow, TVShowDTO> {

    @Override
    TVShowDTO toDto(TVShow tvShow);

    @Override
    TVShow toEntity(TVShowDTO tvShowDTO);
}
