package com.spmystery.episode.util;

import java.util.UUID;

public class StringUtil {

    private StringUtil() {

    }

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }
}
