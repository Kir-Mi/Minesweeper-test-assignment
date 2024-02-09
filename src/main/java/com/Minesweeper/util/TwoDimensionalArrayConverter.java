package com.Minesweeper.util;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TwoDimensionalArrayConverter implements AttributeConverter<String[][], String> {

    private static final String ROW_SEPARATOR = ";";
    private static final String ELEMENT_SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(String[][] attribute) {
        if (attribute == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String[] row : attribute) {
            sb.append(String.join(ELEMENT_SEPARATOR, row)).append(ROW_SEPARATOR);
        }
        return sb.toString();
    }

    @Override
    public String[][] convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        String[] rows = dbData.split(ROW_SEPARATOR);
        String[][] result = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].split(ELEMENT_SEPARATOR);
        }
        return result;
    }
}
