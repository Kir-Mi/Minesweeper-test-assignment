package com.Minesweeper.controller;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.dto.NewGameDto;
import com.Minesweeper.dto.TurnDto;
import com.Minesweeper.service.GameServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameServiceImpl service;

    @PostMapping("/new")
    public FieldDto createGame(@Valid @RequestBody NewGameDto newGameDto) {
        return service.createGame(newGameDto);
    }

    @PostMapping("/turn")
    public FieldDto turnCell(@Valid @RequestBody TurnDto turnDto) {
        return service.turnCell(turnDto);
    }
}