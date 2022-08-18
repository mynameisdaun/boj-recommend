package com.daun.word.utils;

public class StringUtils {
    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
}
