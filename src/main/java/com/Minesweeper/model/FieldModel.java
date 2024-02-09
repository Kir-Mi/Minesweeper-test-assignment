package com.Minesweeper.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldModel {
    private String[][] field;
}