package com.amazonlocker.util;

import java.security.SecureRandom;

/**
 * Generates unpredictable, fixed-length numeric access codes for pickup.
 * SecureRandom (not Random / Math.random) because a guessable code would let
 * someone open a locker that isn't theirs.
 */
public final class AccessCodeGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int CODE_LENGTH = 6;

    private AccessCodeGenerator() { }

    public static String generate() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}
