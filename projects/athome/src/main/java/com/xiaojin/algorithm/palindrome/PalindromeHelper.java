package com.xiaojin.algorithm.palindrome;

import org.apache.logging.log4j.util.Strings;

public class PalindromeHelper {
    public static String addSharpToString(String sourceStr) {
        if (Strings.isBlank(sourceStr)) {
            return Strings.EMPTY;
        }
        char[] sharpChars = new char[sourceStr.length() * 2 + 1];

        for (int i = 0; i < sourceStr.length(); i++) {
            sharpChars[i * 2] = '#';
            sharpChars[i * 2 + 1] = sourceStr.charAt(i);
        }
        sharpChars[sourceStr.length() * 2] = '#';
        return new String(sharpChars);
    }
}
