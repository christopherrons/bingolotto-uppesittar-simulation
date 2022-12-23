package com.bingolotto.util;

public class BingoUtils {

    public static int getColumnIndex(int value) {
        if (value <= 15) {
            return 1;
        }
        if (value <= 30) {
            return 2;
        }
        if (value <= 45) {
            return 3;
        }
        if (value <= 60) {
            return 4;
        }
        if (value <= 75) {
            return 5;
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
