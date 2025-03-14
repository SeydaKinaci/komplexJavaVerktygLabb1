package org.example.komplexJavaLabb1.dto;

import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.mapper.MovieMapper;

/**
 * DTO for {@link org.example.komplexJavaLabb1.entity.Movie}
 */
public record MovieResponse(Long id, String title, int releaseYear, String director, String genre, String description, int duration) {

    public MovieResponse(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getReleaseYear(), movie.getDirector(), movie.getGenre(), movie.getDescription(), movie.getDuration());
    }

    public static MovieResponse map(Movie movie){
        return MovieMapper.map(movie);
    }
}