package com.Minesweeper.service;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.dto.NewGameDto;
import com.Minesweeper.dto.TurnDto;

public interface GameService {
    FieldDto createGame(NewGameDto newGameDto);

    public FieldDto turnCell(TurnDto turnDto);
}