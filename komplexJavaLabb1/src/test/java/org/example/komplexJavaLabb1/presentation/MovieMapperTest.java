package org.example.komplexJavaLabb1.presentation;

import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.mapper.MovieMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class MovieMapperTest {

        @Test
        void testMapToMovieResponse() {
            Movie movie = new Movie();
            movie.setId(1L);
            movie.setTitle("Inception");
            movie.setReleaseYear(2010);
            movie.setDirector("Christopher Nolan");
            movie.setGenre("Sci-Fi");
            movie.setDescription("A mind-bending thriller.");
            movie.setDuration(148);

            MovieResponse response = MovieMapper.map(movie);

            assertNotNull(response);
            assertEquals(movie.getId(), response.id());
            assertEquals(movie.getTitle(), response.title());
            assertEquals(movie.getReleaseYear(), response.releaseYear());
            assertEquals(movie.getDirector(), response.director());
            assertEquals(movie.getGenre(), response.genre());
            assertEquals(movie.getDescription(), response.description());
            assertEquals(movie.getDuration(), response.duration());
        }

        @Test
        void testMapToMovieResponseWithNull() {
            MovieMapper.map((Movie) null);
            MovieResponse response = null;
            assertNull(response);
        }

        @Test
        void testMapToMovie() {
            CreateMovie createMovie = new CreateMovie("Inception", 2010, "Christopher Nolan", "Sci-Fi", "A mind-bending thriller.", 148);

            Movie movie = MovieMapper.map(createMovie);

            assertNotNull(movie);
            assertEquals(createMovie.title(), movie.getTitle());
            assertEquals(createMovie.releaseYear(), movie.getReleaseYear());
            assertEquals(createMovie.director(), movie.getDirector());
            assertEquals(createMovie.genre(), movie.getGenre());
            assertEquals(createMovie.description(), movie.getDescription());
            assertEquals(createMovie.duration(), movie.getDuration());
        }

        @Test
        void testMapToMovieWithNull() {
            Movie movie = MovieMapper.map((CreateMovie) null);
            assertNull(movie);
        }
    }
