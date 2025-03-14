package org.example.komplexJavaLabb1.presentation;

import org.example.komplexJavaLabb1.business.MovieService;
import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.dto.UpdateMovie;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.exceptions.NotFound;
import org.example.komplexJavaLabb1.mapper.MovieMapper;
import org.example.komplexJavaLabb1.persistence.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    private Movie movie;
    private MovieResponse movieResponse;
    private CreateMovie createMovie;
    private UpdateMovie updateMovie;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        movie = new Movie();
        movieResponse = new MovieResponse(movie);
        createMovie = new CreateMovie("Inception", 2010, "science-fiction",
                "Christopher Nolan",
                "A thief who steals corporate secrets through the use of dream-sharing technology",
                120);
        updateMovie = new UpdateMovie("Inception 2", 2021, "Christopher Nolan", "Sci-Fi","A new chapter in the thriller", 145 );
    }

    @Test
    public void testGetMovies() {

        when(movieRepository.findAll()).thenReturn(List.of(movie).stream()); // Mock repository to return a list with this movie

        // Act
        List<MovieResponse> movies = movieService.getMovies();

        // Assert
        assertNotNull(movies);
        assertEquals(1, movies.size());
        assertEquals(movieResponse.title(), movies.get(0).title());  // Check the title
        assertEquals(movieResponse.genre(), movies.get(0).genre());  // Check the genre
        assertEquals(movieResponse.description(), movies.get(0).description());  // Check the description
        assertEquals(movieResponse.duration(), movies.get(0).duration());  // Check the duration
    }

    @Test
    public void testGetMovieById_Success() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        // Act
        MovieResponse result = movieService.getMovieById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(movieResponse.title(), result.title());
        assertEquals(movieResponse.genre(), result.genre());
        assertEquals(movieResponse.description(), result.description());
        assertEquals(movieResponse.duration(), result.duration());
    }

    @Test
    public void testGetMovieById_NotFound() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFound exception = assertThrows(NotFound.class, () -> movieService.getMovieById(1L));
        assertEquals("Book with id 1 not found", exception.getMessage());
    }

    @Test
    public void testCreateMovie() {

        Movie newMovie = new Movie();
        newMovie.setTitle(createMovie.title());
        newMovie.setReleaseYear(createMovie.releaseYear());
        newMovie.setDirector(createMovie.director());
        newMovie.setGenre(createMovie.genre());
        newMovie.setDescription(createMovie.description());
        newMovie.setDuration(createMovie.duration());

        when(movieRepository.insert(any(Movie.class))).thenReturn(newMovie);

        // Act
        Movie createdMovie = movieService.createMovie(createMovie);

        // Assert
        assertNotNull(createdMovie);
        assertEquals(createMovie.title(), createdMovie.getTitle());
        assertEquals(createMovie.releaseYear(), createdMovie.getReleaseYear());
        assertEquals(createMovie.director(), createdMovie.getDirector());
        assertEquals(createMovie.genre(), createdMovie.getGenre());
        assertEquals(createMovie.description(), createdMovie.getDescription());
        assertEquals(createMovie.duration(), createdMovie.getDuration());
    }

    @Test
    public void testUpdateMovieFieldByField_Success() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        // Act
        movieService.updateMovieFieldByField(updateMovie, 1L);

        // Assert
        Mockito.verify(movieRepository, atLeastOnce()).update(any(Movie.class));
        assertEquals("Inception 2", movie.getTitle());
        assertEquals(2021, movie.getReleaseYear());
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals("A new chapter in the thriller", movie.getDescription());
    }

    @Test
    public void testUpdateMovieFieldByField_NotFound() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFound exception = assertThrows(NotFound.class, () -> movieService.updateMovieFieldByField(updateMovie, 1L));
        assertEquals("Book with id 1 not found", exception.getMessage());
    }

    @Test
    public void testDeleteMovie_Success() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        // Act
        movieService.deleteMovie(1L);

        // Assert
        Mockito.verify(movieRepository, times(1)).deleteMovieById(1L);
    }

    @Test
    public void testDeleteMovie_NotFound() {
        // Arrange
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        NotFound exception = assertThrows(NotFound.class, () -> movieService.deleteMovie(1L));
        assertEquals("Movie with id 1 not found", exception.getMessage());
    }
}
