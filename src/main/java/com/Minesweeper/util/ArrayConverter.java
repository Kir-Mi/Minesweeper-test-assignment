package com.Minesweeper.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class ArrayConverter {

    private static final String ROW_SEPARATOR = ";";
    private static final String ELEMENT_SEPARATOR = ",";

    public String convertToString(String[][] array) {
        if (array == null) {
            return null;
        }
        return Arrays.stream(array)
                .map(row -> String.join(ELEMENT_SEPARATOR, row))
                .collect(Collectors.joining(ROW_SEPARATOR));
    }

    public String[][] convertToArray(String str) {
        if (str == null) {
            return null;
        }
        return Arrays.stream(str.split(ROW_SEPARATOR))
                .map(row -> row.split(ELEMENT_SEPARATOR))
                .toArray(String[][]::new);
    }
}
