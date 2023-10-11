package pl.gameandfun.maincode.Module_CinemaLibrary.genre;

import pl.gameandfun.maincode.Module_CinemaLibrary.genre.dto.GenreDto;

public class GenreDtoMapper {
    public static GenreDto mapperDto(Genre genre) {
        return new GenreDto(
                genre.getId(),
                genre.getName(),
                genre.getDescription()
        );
    }
}
