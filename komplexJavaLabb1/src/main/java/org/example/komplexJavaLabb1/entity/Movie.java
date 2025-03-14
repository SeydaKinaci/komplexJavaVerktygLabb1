package org.example.komplexJavaLabb1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title is required.")
    private String title;

    @Column(nullable = false)
    @Size(min=1950, max= 2025)
    private int releaseYear;

    @Size(max= 1000)
    private String description;

    private String genre;

    @Size(min= 5, max = 100)
    @NotBlank(message = "Director is required.")
    private String director;

    @Min(30)
    @Max(240)
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public int getReleaseYear() {return releaseYear;}

    public void setReleaseYear(int releaseYear) {this.releaseYear = releaseYear;}

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return getId() != null && Objects.equals(getId(), movie.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", duration=" + duration +
                '}';
    }
}
