package com.Minesweeper.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinesCountValidator.class)
public @interface MinesCount {
    String message() default "Invalid mines count";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}