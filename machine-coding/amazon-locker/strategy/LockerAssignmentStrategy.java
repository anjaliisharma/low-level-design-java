package com.amazonlocker.strategy;

import com.amazonlocker.enums.LockerSize;
import com.amazonlocker.model.Locker;
import com.amazonlocker.model.LockerLocation;

import java.util.Optional;

/**
 * Strategy pattern: pluggable policy for picking which locker a package
 * should go into. Swapping the implementation changes the assignment
 * behaviour without touching LockerService.
 */
public interface LockerAssignmentStrategy {
    Optional<Locker> findLocker(LockerLocation location, LockerSize packageSize);
}
