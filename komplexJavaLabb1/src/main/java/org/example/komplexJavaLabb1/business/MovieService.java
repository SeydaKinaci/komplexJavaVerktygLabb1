package org.example.komplexJavaLabb1.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.komplexJavaLabb1.persistence.MovieRepository;
import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.dto.UpdateMovie;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.exceptions.NotFound;
import org.example.komplexJavaLabb1.mapper.MovieMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.*;

@ApplicationScoped
public class MovieService {

    private MovieRepository repository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    public MovieService() {
    }


    public List<MovieResponse> getMovies() {
        return repository.findAll()
                .map(MovieResponse::new)
                .filter(Objects::nonNull)
                .toList();
    }

    public MovieResponse getMovieById(Long id) {
        return repository.findById(id)
                .map(MovieResponse::new)
                .orElseThrow(
                        () -> new NotFound("Book with id " + id + " not found"));
    }

    public Movie createMovie(CreateMovie movie) {
        Movie newMovie = MovieMapper.map(movie);
        newMovie = repository.insert(newMovie);
        return newMovie;
    }

    public void updateMovieFieldByField(UpdateMovie movie, Long id) {
        var oldMovie = repository.findById(id).orElseThrow(() -> new NotFound("Book with id " + id + " not found"));

        if (movie.title() != null) {
            oldMovie.setTitle(movie.title());
        }
        if (movie.releaseYear() != 0) {
            oldMovie.setReleaseYear(movie.releaseYear());
        }
        if (movie.director() != null) {
            oldMovie.setDirector(movie.director());
        }
        if (movie.genre() != null) {
            oldMovie.setGenre(movie.genre());
        }
        if (movie.description() != null) {
            oldMovie.setDescription(movie.description());
        }
        if (movie.duration() != 0) {
            oldMovie.setDuration(movie.duration());
        }
        repository.update(oldMovie);
    }

    public void updateFullMovie(UpdateMovie movie, Long id) {
        Movie oldMovie = repository.findById(id).orElseThrow(() -> new NotFound("Book with id " + id + " not found"));

        oldMovie.setTitle(movie.title());
        oldMovie.setReleaseYear(movie.releaseYear());
        oldMovie.setDirector(movie.director());
        repository.update(oldMovie);
    }

    public void deleteMovie(Long id) {
        Movie movie = repository.findById(id).orElseThrow(() -> new NotFound("Movie with id " + id + " not found"));
        repository.deleteMovieById(id);
    }

    public List<MovieResponse> getMoviesByGenre(String genre) {
        return repository.findByGenre(genre)
                .map(MovieResponse::new)
                .toList();
    }

    public List<MovieResponse> getMoviesByDurationRange(int min, int max){
        return repository.findByDurationRange(min, max)
                .map(MovieResponse::new)
                .toList();
    }
    public List<MovieResponse> getMoviesByDirector(String director) {
        return repository.findByDirector(director)
                .map(MovieResponse::new)
                .toList();
    }


}







