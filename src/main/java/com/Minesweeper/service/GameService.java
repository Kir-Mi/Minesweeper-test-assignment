package com.Minesweeper.service;

import com.Minesweeper.repository.GameRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
}
