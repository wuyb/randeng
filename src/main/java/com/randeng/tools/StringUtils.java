package com.randeng.tools;

public final class StringUtils {

    private StringUtils() {}

    public static String capitalize(String word) {
        return Character.toUpperCase(word.charAt(0)) + word.substring(1);
    }
}
