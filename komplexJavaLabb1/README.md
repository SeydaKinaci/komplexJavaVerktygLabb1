Movie Management System
This project contains a Movie Management System API. The API allows you to create, update, delete and list movies. It also provides functionality to filter movies by genre, duration or director.

Features
Movie Listing: List all movies.
Add Movie: Add a new movie.
Movie Update: Update movies' information piece by piece or in full.
Delete Movie: Delete a specific movie.
Filter Movies: Filter by genre, time range and director.

Technologies Used;
Java 23
Jakarta EE 11 (JAX-RS, CDI, Validation)
Mockito, JunitTest, Assert (for tests)
Maven (for dependencies and structure)

Structure
MovieService Class
The MovieService class contains the business logic related to movies. It provides the following functionality:

getMovies(): Gets all movies in the database.
getMovieById(Long id): Gets the movie whose ID is given.
createMovie(CreateMovie movie): Creates a new movie.
updateMovieFieldByField(UpdateMovie movie, Long id): Partially updates the movie with the given ID.
updateFullMovie(UpdateMovie movie, Long id): Fully updates the movie with the given ID.
deleteMovie(Long id): Deletes the movie with the given ID.
getMoviesByGenre(String genre): Gets movies of a specific genre.
getMoviesByDurationRange(int min, int max): Gets movies in the specified duration range.
getMoviesByDirector(String director): Gets movies by a specific director.

CreateMovie DTO (Data Transfer Object)
The CreateMovie class, used to add a new movie, has the following fields:

title: The name of the movie (cannot be empty).
releaseYear: The release year of the movie (must be a value between 1950 and 2025).
genre: The genre of the movie (optional).
director: Director of the movie (cannot be empty and must be between 5 and 100 characters).
description: Description of the movie (optional, up to 1000 characters).
duration: Duration of the movie (must be between 30 and 240 minutes).

API Endpoints
The following describes the API endpoints and functions offered by the application:

1. GET /movies
   Description: Used to list all movies.
   Response: 200 OK and a list of movies (in JSON format).
2. GET /movies/{id}
   Description: Used to list a movie by ID.
   Parameters:
   - id: Unique ID of the movie to retrieve.
   Response: 200 OK and movie information (in JSON format).
3. POST /movies
   Description: Used to add a new movie.
   Data to send: CreateMovie object (in JSON format).
   Answer: 201 Created and the information of the newly added movie.
4. PUT /movies/{id}
   Description: Used to fully update an existing movie.
   Parameters:
   - id: Unique ID of the movie to update.
   Data to send: UpdateMovie object (in JSON format).
   Response: 200 OK and updated movie information.
5. PATCH /movies/{id}
   Description: Used to update an existing movie piece by piece.
   Parameters:
   - id: Unique ID of the movie to update.
   Data to send: UpdateMovie object (in JSON format).
   Answer: 200 OK and updated movie information.
6. DELETE /movies/{id}
   Description: Deletes the movie with the specified ID.
   Parameters:
   - id: Unique ID of the movie to be deleted.
   Response: 204 No Content (successfully deleted).
7. GET /movies/genre/{genre}
   Description: Lists movies of the specified genre.
   Parameters:
   - genre: The genre to which the movies belong.
   Answer: 200 OK and genre movies.
8. GET /movies/duration/{min}/{max}
   Description: Lists movies by duration range.
   Parameters:
   - min: Minimum duration of the movie (minutes).
   - max: Maximum duration of the movie (minutes).
   Answer: Movies in the range of 200 OK and duration.
9. GET /movies/director/{director}
   Description: Lists movies by a specific director.
   Parameters:
   - director: Director of the movie.
   Answer: 200 OK and movies by the director

Tests
In this project, JUnit 5 and Mockito were used to test API endpoints.

You can use the following command to run the tests:
- mvn test