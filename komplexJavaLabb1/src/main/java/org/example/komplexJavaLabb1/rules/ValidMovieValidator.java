package org.example.komplexJavaLabb1.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.komplexJavaLabb1.dto.CreateMovie;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {

    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext constraintValidatorContext) {
        if (Character.isUpperCase(createMovie.title().charAt(0)) &&createMovie.releaseYear() != createMovie.title().length()){
           return true;}
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Length and title must be the same.")
                .addConstraintViolation();
        return false;
    }
}
