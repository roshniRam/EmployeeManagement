package com.example.CercliCodingCase.utility;

import java.util.regex.Pattern;

/**
 * Utility class for all email related functionality
 */
public class EmailUtil {

    // Email validation pattern
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }
}
