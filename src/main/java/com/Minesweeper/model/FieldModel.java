package com.Minesweeper.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldModel {
    private String[][] field;
}