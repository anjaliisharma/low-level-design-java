package com.amazonlocker.service;

import com.amazonlocker.enums.PackageStatus;
import com.amazonlocker.exception.InvalidAccessCodeException;
import com.amazonlocker.exception.LockerNotFoundException;
import com.amazonlocker.exception.NoLockerAvailableException;
import com.amazonlocker.model.Locker;
import com.amazonlocker.model.LockerAssignment;
import com.amazonlocker.model.LockerLocation;
import com.amazonlocker.model.Package;
import com.amazonlocker.strategy.LockerAssignmentStrategy;
import com.amazonlocker.util.AccessCodeGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Coordinates the two core use cases: drop-off and pickup.
 */
public class LockerService {

    private final Map<String, LockerLocation> locations = new HashMap<>();
    private final Map<String, LockerAssignment> assignmentsByCode = new HashMap<>();

    private final LockerAssignmentStrategy assignmentStrategy;

    public LockerService(LockerAssignmentStrategy assignmentStrategy) {
        this.assignmentStrategy = assignmentStrategy;
    }

    public void registerLocation(LockerLocation location) {
        locations.put(location.getLocationId(), location);
    }

    /**
     * Finds a suitable free locker, assigns the package, and returns its code.
     */
    public LockerAssignment dropOffPackage(String locationId, Package pkg) {
        LockerLocation location = getLocationOrThrow(locationId);

        Optional<Locker> lockerOpt = assignmentStrategy.findLocker(location, pkg.getSize());
        Locker locker = lockerOpt.orElseThrow(() ->
                new NoLockerAvailableException(
                        "No available locker of sufficient size at location " + locationId));

        locker.assignPackage(pkg);
        pkg.setStatus(PackageStatus.IN_LOCKER);

        String accessCode = generateUniqueAccessCode();
        LockerAssignment assignment = new LockerAssignment(accessCode, locker, pkg);
        assignmentsByCode.put(accessCode, assignment);
        return assignment;
    }

    /**
     * Validates the access code, releases the locker, and marks pickup complete.
     */
    public Package pickUpPackage(String locationId, String lockerId, String accessCode) {
        LockerLocation location = getLocationOrThrow(locationId);
        Locker locker = location.getLocker(lockerId);
        if (locker == null) {
            throw new LockerNotFoundException("No locker " + lockerId + " at location " + locationId);
        }

        LockerAssignment assignment = assignmentsByCode.get(accessCode);
        if (assignment == null || assignment.getLocker() != locker) {
            throw new InvalidAccessCodeException("Access code is invalid for locker " + lockerId);
        }

        Package pkg = assignment.getPkg();
        pkg.setStatus(PackageStatus.PICKED_UP);
        locker.release();
        assignmentsByCode.remove(accessCode);
        return pkg;
    }

    private LockerLocation getLocationOrThrow(String locationId) {
        LockerLocation location = locations.get(locationId);
        if (location == null) {
            throw new LockerNotFoundException("No such location: " + locationId);
        }
        return location;
    }

    private String generateUniqueAccessCode() {
        String code;
        do {
            code = AccessCodeGenerator.generate();
        } while (assignmentsByCode.containsKey(code));
        return code;
    }
}
