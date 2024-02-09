package com.Minesweeper.dto;

import com.Minesweeper.model.FieldModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldDto {
    private String gameId;
    private int width;
    private int height;
    private int minesCount;
    private boolean completed;
    private FieldModel fieldModel;
}
