package com.amazonlocker.model;

import com.amazonlocker.enums.LockerSize;
import com.amazonlocker.enums.LockerStatus;

/**
 * A single physical locker cell.
 */
public class Locker {
    private final String lockerId;
    private final LockerSize size;
    private LockerStatus status;
    private Package currentPackage;

    public Locker(String lockerId, LockerSize size) {
        this.lockerId = lockerId;
        this.size = size;
        this.status = LockerStatus.AVAILABLE;
        this.currentPackage = null;
    }

    public String getLockerId() { return lockerId; }
    public LockerSize getSize() { return size; }

    public LockerStatus getStatus() { return status; }
    public Package getCurrentPackage() { return currentPackage; }

    public boolean isAvailable() {
        return status == LockerStatus.AVAILABLE;
    }

    public void assignPackage(Package pkg) {
        if (!isAvailable()) {
            throw new IllegalStateException("Locker " + lockerId + " is not available");
        }
        this.currentPackage = pkg;
        this.status = LockerStatus.OCCUPIED;
    }

    public void release() {
        this.currentPackage = null;
        this.status = LockerStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Locker{" + "id='" + lockerId + '\'' + ", size=" + size + ", status=" + status + '}';
    }
}
