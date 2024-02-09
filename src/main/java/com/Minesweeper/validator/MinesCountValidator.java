package com.Minesweeper.validator;

import com.Minesweeper.dto.NewGameDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinesCountValidator implements ConstraintValidator<MinesCount, NewGameDto> {

    @Override
    public void initialize(MinesCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(NewGameDto value, ConstraintValidatorContext context) {
        int width = value.getWidth();
        int height = value.getHeight();
        int minesCount = value.getMinesCount();
        return minesCount >= 1 && minesCount <= width * height - 1;
    }
}