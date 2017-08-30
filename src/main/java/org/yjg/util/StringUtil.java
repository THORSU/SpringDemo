package org.yjg.util;

import java.util.UUID;

public class StringUtil {
    public static boolean isNullOrBlank(String str) {
        return str == null || str.equals("");
    }

    public static String createUUID() {
        return UUID.randomUUID().toString();
    }
}
