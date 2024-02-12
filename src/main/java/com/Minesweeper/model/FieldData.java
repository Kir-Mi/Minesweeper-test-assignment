package com.Minesweeper.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@Table(name = "field_data")
@AllArgsConstructor
@NoArgsConstructor
public class FieldData {
    @Id
    @Column(name = "game_id")
    private String gameId;
    @Column(name = "field")
    private String field;
}
