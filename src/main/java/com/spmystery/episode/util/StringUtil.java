package com.spmystery.episode.util;

import java.util.UUID;

public class StringUtil {

    private StringUtil() {

    }

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
