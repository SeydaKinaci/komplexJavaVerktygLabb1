package org.example.komplexJavaLabb1.presentation;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.example.komplexJavaLabb1.business.MovieService;
import org.example.komplexJavaLabb1.dto.CreateMovie;
import org.example.komplexJavaLabb1.dto.MovieResponse;
import org.example.komplexJavaLabb1.dto.UpdateMovie;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.persistence.MovieRepository;

import java.util.List;

@Path("movies")
@Log
public class MovieResource {

    private MovieService movieService;

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieResource() {
    }

    //http://localhost:8080/api/movies/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getAllMovies() {
        return movieService.getMovies();
    }

    //http://localhost:8080/api/movies/87
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MovieResponse getOneMovie(@PathParam("id") Long id) {
        return movieService.getMovieById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewMovie(@Valid @NotNull CreateMovie movie) {
        if (movie == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Movie can not be null").build();
        }
        Movie newMovie = movieService.createMovie(movie);
        MovieResource.log.info("Creating new movie: " + newMovie);
        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/api/movies/" + newMovie.getId())
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFullMovie(@Valid UpdateMovie movie, @PathParam("id") Long id) {
        movieService.updateFullMovie(movie, id);
        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovieFieldByField(@Valid UpdateMovie movie, @PathParam("id") Long id) {
        movieService.updateMovieFieldByField(movie, id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") Long id) {
        movieService.deleteMovie(id);
        MovieResource.log.info("Deleting movie with id: " + id);
        return Response.noContent().build();
    }

    // http://localhost:8080/api/movies/genre/{genre}
    @GET
    @Path("/genre/{genre}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMoviesByGenre(@PathParam("genre") String genre) {
        return movieService.getMoviesByGenre(genre);}

    // http://localhost:8080/api/movies/duration?min=90&max=120
    @GET
    @Path("/duration")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMoviesByDurationRange(@QueryParam("min") int min, @QueryParam("max") int max) {
        return movieService.getMoviesByDurationRange(min, max);}

    @GET
    @Path("/director/{director}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MovieResponse> getMoviesByDirector(@PathParam("director") String director) {
        return movieService.getMoviesByDirector(director);
    }
}
