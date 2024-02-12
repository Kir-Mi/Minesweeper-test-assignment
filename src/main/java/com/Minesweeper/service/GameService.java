package com.Minesweeper.service;

import com.Minesweeper.dto.*;

public interface GameService {
    FieldDto createGame(NewGameDto newGameDto);

    FieldDto turnCell(TurnDto turnDto);
}