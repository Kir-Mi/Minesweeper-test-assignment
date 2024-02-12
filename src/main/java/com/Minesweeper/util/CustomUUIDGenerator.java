package com.Minesweeper.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CustomUUIDGenerator {
    private static final String HEX_CHARS = "0123456789ABCDEF";

    public String generateCustomUUID() {
        StringBuilder uuidBuilder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            uuidBuilder.append(HEX_CHARS.charAt(new Random().nextInt(HEX_CHARS.length())));
        }
        uuidBuilder.append("-");

        for (int i = 0; i < 4; i++) {
            uuidBuilder.append(HEX_CHARS.charAt(new Random().nextInt(HEX_CHARS.length())));
        }
        uuidBuilder.append("-");

        for (int i = 0; i < 4; i++) {
            uuidBuilder.append(HEX_CHARS.charAt(new Random().nextInt(HEX_CHARS.length())));
        }
        uuidBuilder.append("-");

        for (int i = 0; i < 4; i++) {
            uuidBuilder.append(HEX_CHARS.charAt(new Random().nextInt(HEX_CHARS.length())));
        }
        uuidBuilder.append("-");

        for (int i = 0; i < 12; i++) {
            uuidBuilder.append(HEX_CHARS.charAt(new Random().nextInt(HEX_CHARS.length())));
        }

        return uuidBuilder.toString();
    }
}
