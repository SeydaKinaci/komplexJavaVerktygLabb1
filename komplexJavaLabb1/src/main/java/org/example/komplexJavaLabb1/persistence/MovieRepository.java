package org.example.komplexJavaLabb1.persistence;

import jakarta.data.repository.*;
import org.example.komplexJavaLabb1.entity.Movie;

import java.util.stream.Stream;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Find
    Movie
    findByTitle(String title);

    @Query("select m from Movie m where releaseYear > :releaseYear")
    Stream<Movie> findByReleaseYearGreaterThan(int releaseYear);

    @Delete
    void deleteMovieById(Long id);

    @Query("select m from Movie m where m.genre = :genre")
    Stream<Movie> findByGenre(String genre);

    @Query("select m from Movie m where m.director = :director")
    Stream<Movie> findByDirector(String director);

    @Query("select m from Movie m where m.duration between :min and :max")
    Stream<Movie> findByDurationRange(int min, int max);





}
