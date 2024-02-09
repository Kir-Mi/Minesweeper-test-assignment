package com.Minesweeper.service;

import com.Minesweeper.dto.FieldDto;
import com.Minesweeper.dto.NewGameDto;
import com.Minesweeper.dto.TurnDto;
import com.Minesweeper.repository.GameRepository;
import com.Minesweeper.util.CustomUUIDGenerator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Override
    public FieldDto createGame(NewGameDto newGameDto) {
        String[][] newField = setUpMines(newGameDto);
        String gameId = generateId();

    }

    @Override
    public FieldDto turnCell(TurnDto turnDto) {

    }

    public String[][] setUpMines(NewGameDto newGameDto) {
        int rows = newGameDto.getHeight();
        int cols = newGameDto.getWidth();
        int minesCount = newGameDto.getMinesCount();
        String[][] newField = new String[rows][cols];
        List<String> allCells = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                allCells.add(i + "," + j);
            }
        }

        Collections.shuffle(allCells);

        for (int i = 0; i < minesCount; i++) {
            String[] coordinates = allCells.get(i).split(",");
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);
            newField[row][col] = "M";
        }

        fillEmptyCells(newField, rows, cols);
        return newField;
    }

    public void fillEmptyCells(String[][] field, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!field[i][j].equals("M")) {
                    int minesAround = countMinesAround(i, j, field);
                    field[i][j] = String.valueOf(minesAround);
                }
            }
        }
    }

    private int countMinesAround(int row, int col, String[][] field) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < field.length && j >= 0 && j < field[0].length && field[i][j].equals("M")) {
                    count++;
                }
            }
        }
        return count;
    }

    public String generateId() {
        String id = CustomUUIDGenerator.generateCustomUUID();
        while (gameRepository.existsById(id)) {
            id = CustomUUIDGenerator.generateCustomUUID();
        }
        return id;
    }
}