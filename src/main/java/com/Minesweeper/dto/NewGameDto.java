package com.Minesweeper.dto;

import com.Minesweeper.validator.MinesCount;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewGameDto {
    @Size(min = 2, max = 30)
    private int width;
    @Size(min = 2, max = 30)
    private int height;
    @MinesCount
    private int minesCount;
}