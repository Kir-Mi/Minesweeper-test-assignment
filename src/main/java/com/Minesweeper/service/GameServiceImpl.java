package com.Minesweeper.service;

import com.Minesweeper.dto.*;
import com.Minesweeper.exceptions.*;
import com.Minesweeper.mapper.FieldMapper;
import com.Minesweeper.model.*;
import com.Minesweeper.repository.*;
import com.Minesweeper.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final DataRepository dataRepository;

    @Override
    public FieldDto createGame(NewGameDto newGameDto) {
        if (newGameDto.getMinesCount() > newGameDto.getHeight() * newGameDto.getWidth() - 1) {
            throw new MyApplicationException("Мин слишком много", HttpStatus.BAD_REQUEST);
        }
        String[][] newField = setUpMines(newGameDto);
        String gameId = generateId();
        FieldData fieldData = new FieldData(gameId, ArrayConverter.convertToString(newField));
        dataRepository.save(fieldData);

        String[][] hiddenField = hideField(newField);
        String fieldString = ArrayConverter.convertToString(hiddenField);
        boolean completed = false;
        FieldModel fieldModel = FieldModel.builder()
                .gameId(gameId)
                .width(newGameDto.getWidth())
                .height(newGameDto.getHeight())
                .minesCount(newGameDto.getMinesCount())
                .completed(completed)
                .fieldModel(fieldString)
                .build();

        return FieldMapper.toFieldDto(gameRepository.save(fieldModel));
    }

    @Override
    public FieldDto turnCell(TurnDto turnDto) {
        FieldData fieldData = dataRepository.findById(turnDto.getGame_id())
                .orElseThrow(() -> new MyApplicationException("Игра не найдена", HttpStatus.BAD_REQUEST));
        FieldModel fieldModel = gameRepository.findById(turnDto.getGame_id())
                .orElseThrow(() -> new MyApplicationException("Данные о игре не найдены", HttpStatus.BAD_REQUEST));
        if (fieldModel.isCompleted()) {
            throw new MyApplicationException("Игра уже завершена", HttpStatus.BAD_REQUEST);
        }
        int col = turnDto.getCol();
        int row = turnDto.getRow();
        if (col > fieldModel.getWidth() - 1 || row > fieldModel.getHeight() - 1) {
            throw new MyApplicationException("Неверные координаты", HttpStatus.BAD_REQUEST);
        }
        String[][] data = ArrayConverter.convertToArray(fieldData.getField());
        if (data[row][col].equals("M")) {
            return loseGame(fieldModel, fieldData);
        }
        String[][] newField = openCells(fieldModel, data, row, col);
        if (allCellsOpened(newField)) {
            fieldModel.setCompleted(true);
        }
        fieldModel.setFieldModel(ArrayConverter.convertToString(newField));
        gameRepository.save(fieldModel);
        return FieldMapper.toFieldDto(fieldModel);
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
                if (field[i][j] == null) {
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
                if (i >= 0 && i < field.length && j >= 0 && j < field[0].length && field[i][j] != null && field[i][j].equals("M")) {
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

    public String[][] hideField(String[][] field) {
        for (String[] strings : field) {
            Arrays.fill(strings, " ");
        }
        return field;
    }

    public FieldDto loseGame(FieldModel fieldModel, FieldData fieldData) {
        String openField = ArrayConverter.convertToString(replaceMWithX(ArrayConverter.convertToArray(fieldData.getField())));
        fieldModel.setFieldModel(openField);
        fieldModel.setCompleted(true);
        return FieldMapper.toFieldDto(fieldModel);
    }

    public String[][] replaceMWithX(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if ("M".equals(field[i][j])) {
                    field[i][j] = "X";
                }
            }
        }
        return field;
    }

    public String[][] openCells(FieldModel model, String[][] data, int row, int col) {
        String[][] hiddenField = ArrayConverter.convertToArray(model.getFieldModel());
        if (!hiddenField[row][col].equals(" ")) {
            throw new MyApplicationException("Поле уже открыто", HttpStatus.BAD_REQUEST);
        }
        if (!data[row][col].equals("0")) {
            hiddenField[row][col] = data[row][col];
        } else {
            openAdjacentCells(hiddenField, data, row, col);
        }
        return hiddenField;
    }

    private void openAdjacentCells(String[][] hiddenField, String[][] data, int row, int col) {
        int rows = hiddenField.length;
        int cols = hiddenField[0].length;
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                if (hiddenField[newRow][newCol].equals(" ")) {
                    if (!data[newRow][newCol].equals("0")) {
                        hiddenField[newRow][newCol] = data[newRow][newCol];
                    } else {
                        hiddenField[newRow][newCol] = "0";
                        openAdjacentCells(hiddenField, data, newRow, newCol);
                    }
                }
            }
        }
    }

    public boolean allCellsOpened(String[][] field) {
        for (String[] row : field) {
            for (String cell : row) {
                if (cell.equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}