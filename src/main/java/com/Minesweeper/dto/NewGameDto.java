package com.Minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Validated
public class NewGameDto {
    @Min(value = 2, message = "Значение поля должно быть больше 2")
    @Max(value = 30, message = "Значение поля должно быть меньше 30")
    private int width;
    @Min(value = 2, message = "Значение поля должно быть больше 2")
    @Max(value = 30, message = "Значение поля должно быть меньше 30")
    private int height;
    @JsonProperty("mines_count")
    private int minesCount;
}