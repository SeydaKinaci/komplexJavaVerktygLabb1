package org.example.komplexJavaLabb1.presentation;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.example.komplexJavaLabb1.business.MovieService;
import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.dto.UpdateMovie;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.exceptions.NotFound;
import org.example.komplexJavaLabb1.exceptions.mappers.NotFoundMapper;
import org.example.komplexJavaLabb1.rules.ValidMovie;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.spi.Dispatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieResourceTest {

    @Mock
    MovieService movieService;

    @InjectMocks
    private MovieResource movieResource;

    Dispatcher dispatcher;

    @BeforeEach
    public void setup() {
        dispatcher = MockDispatcherFactory.createDispatcher();
        MovieResource movieResource = new MovieResource(movieService);
        dispatcher.getRegistry().addSingletonResource(movieResource);

        ExceptionMapper<NotFound> mapper = new NotFoundMapper();
        dispatcher.getProviderFactory().registerProviderInstance(mapper);
    }

    @Test
    void getAllMovies() throws URISyntaxException, UnsupportedEncodingException {
        when(movieService.getMovies()).thenReturn(List.of());

        MockHttpRequest request = MockHttpRequest.get("/movies");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertEquals("[]", response.getContentAsString());
    }

    @Test
    void getOneMovie() throws URISyntaxException, UnsupportedEncodingException {
        Long movieId = 87L;
        MovieResponse movieResponse = new MovieResponse(movieId, "Inception",2010, "Christopher Nolan", "Action", "A sci-fi epic about a man who becomes a god of dreams.", 148);

        when(movieService.getMovieById(movieId)).thenReturn(movieResponse);

        MockHttpRequest request = MockHttpRequest.get("/movies/" + movieId);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("Inception"));
    }

    @Test
    void createNewMovie() throws URISyntaxException, UnsupportedEncodingException {
        CreateMovie createMovie = new CreateMovie("The Matrix", 1999, "Sci-Fi", "Lana Wachowski", "A hacker discovers the truth about his reality.", 136);

        Movie newMovie = new Movie();
        newMovie.setId(1L);
        newMovie.setTitle("The Matrix");
        newMovie.setReleaseYear(1999);
        newMovie.setGenre("Sci-Fi");
        newMovie.setDirector("Lana Wachowski");
        newMovie.setDescription("A hacker discovers the truth about his reality.");
        newMovie.setDuration(136);

        when(movieService.createMovie(createMovie)).thenReturn(newMovie);

        MockHttpRequest request = MockHttpRequest.post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "The Matrix",
                            "releaseYear": 1999,
                            "genre": "Sci-Fi",
                            "director": "Lana Wachowski",
                            "description": "A hacker discovers the truth about his reality.",
                            "duration": 136
                        }""".getBytes());
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(201, response.getStatus());
        assertTrue(response.getOutputHeaders().getFirst("Location").toString().contains("/api/movies/1"));
    }

    @Test
    void updateFullMovie() throws URISyntaxException, UnsupportedEncodingException {
        UpdateMovie updateMovie = new UpdateMovie("Inception", 2010, "Christopher Nolan", "Sci-Fi", "Mind-bending thriller", 148);

        doNothing().when(movieService).updateFullMovie(updateMovie, 1L);

        MockHttpRequest request = MockHttpRequest.put("/movies/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "title": "Inception",
                        "year": 2010,
                        "director": "Christopher Nolan",
                        "genre": "Sci-Fi",
                        "description": "Mind-bending thriller",
                        "duration": 148
                    }""".getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(204, response.getStatus()); // No Content
    }

    @Test
    void updateMovieFieldByField() throws URISyntaxException, UnsupportedEncodingException {
        Long movieId = 87L;
        UpdateMovie updateMovie = new UpdateMovie("The Dark Knight Rises", 2012, "Action", "Christopher Nolan", "Batman returns to save Gotham.", 164);

        doNothing().when(movieService).updateMovieFieldByField(updateMovie, movieId);

        MockHttpRequest request = MockHttpRequest.patch("/movies/" + movieId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "The Dark Knight Rises",
                            "releaseYear": 2012,
                            "genre": "Action",
                            "director": "Christopher Nolan",
                            "description": "Batman returns to save Gotham.",
                            "duration": 164
                        }""".getBytes());
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(204, response.getStatus());
    }

    @Test
    void deleteMovie() throws URISyntaxException, UnsupportedEncodingException {
        Long movieId = 87L;

        doNothing().when(movieService).deleteMovie(movieId);

        MockHttpRequest request = MockHttpRequest.delete("/movies/" + movieId);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(204, response.getStatus());
    }

    @Test
    void getMoviesByGenre() throws URISyntaxException, UnsupportedEncodingException {
        String genre = "Sci-Fi";
        MovieResponse movieResponse = new MovieResponse(1L, "Inception", 2010, "Sci-Fi", "Christopher Nolan", "A mind-bending thriller", 148);

        when(movieService.getMoviesByGenre(genre)).thenReturn(List.of(movieResponse));

        MockHttpRequest request = MockHttpRequest.get("/movies/genre/" + genre);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("Inception"));
    }

    @Test
    void getMoviesByDurationRange() throws URISyntaxException, UnsupportedEncodingException {
        int minDuration = 90;
        int maxDuration = 120;
        MovieResponse movieResponse = new MovieResponse(1L, "The Matrix", 1999, "Sci-Fi", "Lana Wachowski", "A hacker discovers the truth about his reality.", 120);

        when(movieService.getMoviesByDurationRange(minDuration, maxDuration)).thenReturn(List.of(movieResponse));

        MockHttpRequest request = MockHttpRequest.get("/movies/duration?min=" + minDuration + "&max=" + maxDuration);
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        assertEquals(200, response.getStatus());
        assertTrue(response.getContentAsString().contains("The Matrix"));
    }

}