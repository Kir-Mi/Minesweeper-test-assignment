package com.Minesweeper.mapper;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.model.FieldModel;
import com.Minesweeper.util.ArrayConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FieldMapper {

    public FieldDto toFieldDto(FieldModel model) {
        return FieldDto.builder()
                .game_id(model.getGameId())
                .width(model.getWidth())
                .height(model.getHeight())
                .mines_count(model.getMinesCount())
                .completed(model.isCompleted())
                .field(ArrayConverter.convertToArray(model.getFieldModel()))
                .build();
    }
}
