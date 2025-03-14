package org.example.komplexJavaLabb1.exceptions;

import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.persistence.MovieRepository;

public class EntityAlreadyExist extends RuntimeException {

    public EntityAlreadyExist(String message) {
        super(message);
    }

    public static void checkIfMovieExist(Movie movie, MovieRepository repository) {
        Movie existingMovie = repository.findByTitle(movie.getTitle());
        if (existingMovie != null && !existingMovie.equals(movie)) {
            throw new EntityAlreadyExist("Movie with this title already exists");
        }
    }
}
