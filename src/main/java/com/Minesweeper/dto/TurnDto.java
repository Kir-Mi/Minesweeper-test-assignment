package com.Minesweeper.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurnDto {
    @Pattern(regexp = "\\b[0-9A-F]{8}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{4}-[0-9A-F]{12}\\b")
    private String gameId;
    @PositiveOrZero
    private int col;
    @PositiveOrZero
    private int row;
}