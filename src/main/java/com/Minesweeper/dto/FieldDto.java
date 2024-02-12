package com.Minesweeper.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldDto {
    private String game_id;
    private int width;
    private int height;
    private int mines_count;
    private boolean completed;
    private String[][] field;
}
