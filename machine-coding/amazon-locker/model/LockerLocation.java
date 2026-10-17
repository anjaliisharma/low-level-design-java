package com.amazonlocker.model;

import com.amazonlocker.enums.LockerSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A physical bank of lockers at one address (e.g. a Whole Foods pickup point).
 * Owns the collection of Lockers; does not know about packages, codes or
 * customers — that orchestration belongs to LockerService.
 */
public class LockerLocation {
    private final String locationId;
    private final String address;
    private final Map<String, Locker> lockers = new LinkedHashMap<>();

    public LockerLocation(String locationId, String address) {
        this.locationId = locationId;
        this.address = address;
    }

    public String getLocationId() { return locationId; }
    public String getAddress() { return address; }

    public void addLocker(Locker locker) {
        lockers.put(locker.getLockerId(), locker);
    }

    public Locker getLocker(String lockerId) {
        return lockers.get(lockerId);
    }

    public List<Locker> getAllLockers() {
        return Collections.unmodifiableList(new ArrayList<>(lockers.values()));
    }

    public List<Locker> getAvailableLockersOfSize(LockerSize size) {
        List<Locker> result = new ArrayList<>();
        for (Locker locker : lockers.values()) {
            if (locker.getSize() == size && locker.isAvailable()) {
                result.add(locker);
            }
        }
        return result;
    }

    public List<Locker> getAllAvailableLockers() {
        List<Locker> result = new ArrayList<>();
        for (Locker locker : lockers.values()) {
            if (locker.isAvailable()) {
                result.add(locker);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "LockerLocation{" + "id='" + locationId + '\'' + ", address='" + address + '\'' + '}';
    }
}
