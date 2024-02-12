package com.Minesweeper.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnDto {
    @Pattern(regexp = "\\b[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}\\b")
    private String game_id;
    @PositiveOrZero(message = "Координаты должны быть положительные")
    private int col;
    @PositiveOrZero(message = "Координаты должны быть положительные")
    private int row;
}