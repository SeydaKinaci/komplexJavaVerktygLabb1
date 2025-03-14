package org.example.komplexJavaLabb1.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.komplexJavaLabb1.dto.UpdateMovie;

public class UpdateMovieValidator implements ConstraintValidator<ValidMovie, UpdateMovie> {
    @Override
    public boolean isValid(UpdateMovie updateMovie, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
