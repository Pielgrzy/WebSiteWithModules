package pl.gameandfun.maincode.Module_CinemaLibrary.movie;

import pl.gameandfun.maincode.Module_CinemaLibrary.movie.dto.MovieDto;

public class MovieDtoMapper {
    static MovieDto mapperDto(Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getOriginalTitle(),
                movie.getShortDescription(),
                movie.getDescription(),
                movie.getYoutubeTrailerId(),
                movie.getReleaseYear(),
                movie.getGenre().getName(),
                movie.isPromoted(),
                movie.getPoster());
    }
}
