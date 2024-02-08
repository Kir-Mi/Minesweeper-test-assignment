package com.Minesweeper.controller;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.dto.NewGameDto;
import com.Minesweeper.dto.TurnDto;
import com.Minesweeper.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    @PostMapping("/new")
    public FieldDto createGame(NewGameDto newGameDto) {

    }

    @PostMapping("/turn")
    public FieldDto turnCell(TurnDto turnDto) {

    }
}