package org.example.komplexJavaLabb1.dto;

import jakarta.validation.constraints.*;
import org.example.komplexJavaLabb1.rules.ValidMovie;


@ValidMovie
public record CreateMovie(
        @NotBlank String title,
        @NotNull @Size(min=1950, max= 2025) int releaseYear,
        String genre,
        @NotBlank @Size(min= 5, max = 100) String director,
        @Size(max=1000) String description,
        @Size(min= 30, max = 240) int duration
){}
