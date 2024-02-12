package com.Minesweeper.model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@Builder
@Table(name = "field_dto")
@AllArgsConstructor
@NoArgsConstructor
public class FieldModel {
    @Id
    @Column(name = "game_id")
    private String gameId;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;
    @Column(name = "mines_count")
    private int minesCount;
    @Column(name = "completed")
    private boolean completed;
    @Column(name = "field", length = 10000)
    private String fieldModel;
}
