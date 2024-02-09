package com.Minesweeper.service;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.dto.NewGameDto;
import com.Minesweeper.dto.TurnDto;
import com.Minesweeper.repository.GameRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;

    public FieldDto createGame(NewGameDto newGameDto) {

    }
    public FieldDto turnCell(TurnDto turnDto) {

    }
}