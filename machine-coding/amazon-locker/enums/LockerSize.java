package com.amazonlocker.enums;

/**
 * Physical locker sizes, ordered smallest to largest.
 * Ordinal order is relied upon by the assignment strategies to answer
 * "is this locker big enough for that package?".
 */
public enum LockerSize {
    SMALL,
    MEDIUM,
    LARGE;

    public boolean canFit(LockerSize packageSize) {

        return this.ordinal() >= packageSize.ordinal();
    }
}
