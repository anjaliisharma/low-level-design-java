package com.amazonlocker.strategy;

import com.amazonlocker.enums.LockerSize;
import com.amazonlocker.model.Locker;
import com.amazonlocker.model.LockerLocation;

import java.util.List;
import java.util.Optional;

/**
 * Picks the SMALLEST available locker that still fits the package, so that
 * large lockers stay free for large packages instead of being wasted on
 * small ones. This is usually the default policy in real locker systems.
 */
public class BestFitAssignmentStrategy implements LockerAssignmentStrategy {

    @Override
    public Optional<Locker> findLocker(LockerLocation location, LockerSize packageSize) {
        Locker bestLocker = null;

        for (LockerSize size : LockerSize.values()) {
            if (!size.canFit(packageSize)) {
                continue;
            }
            List<Locker> candidates = location.getAvailableLockersOfSize(size);
            if (!candidates.isEmpty()) {
                bestLocker = candidates.get(0);
                break; // LockerSize.values() is already smallest -> largest
            }
        }
        return Optional.ofNullable(bestLocker);
    }
}
