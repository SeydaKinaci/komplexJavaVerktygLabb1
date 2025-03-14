package org.example.komplexJavaLabb1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.komplexJavaLabb1.rules.ValidMovie;


public record UpdateMovie(
        @NotBlank String title,
        @NotNull @Size(min=1950, max= 2025) int releaseYear,
        String genre,
        @NotBlank @Size(min= 5, max = 100) String director,
        @Size(max=1000) String description,
        @Size(min= 30, max = 240) int duration) {
}
