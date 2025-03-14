package org.example.komplexJavaLabb1.mapper;

import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.entity.Movie;

public class MovieMapper {

    public static MovieResponse map(Movie movie){
        if(movie == null){return null;}
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getReleaseYear(), movie.getDirector(),movie.getGenre(), movie.getDescription(), movie.getDuration());}

    public static Movie map(CreateMovie createMovie) {
        if (createMovie == null) { return null; }

        Movie movie = new Movie();
        movie.setTitle(createMovie.title());
        movie.setReleaseYear(createMovie.releaseYear());
        movie.setDirector(createMovie.director());
        movie.setGenre(createMovie.genre());
        movie.setDescription(createMovie.description());
        movie.setDuration(createMovie.duration());

        return movie;
    }
}
