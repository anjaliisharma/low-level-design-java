package com.amazonlocker.model;

/** Links a package, its assigned locker, and the one-time pickup code. */
public class LockerAssignment {
    private final String accessCode;
    private final Locker locker;
    private final Package pkg;

    public LockerAssignment(String accessCode, Locker locker, Package pkg) {
        this.accessCode = accessCode;
        this.locker = locker;
        this.pkg = pkg;
    }

    public String getAccessCode() { return accessCode; }
    public Locker getLocker() { return locker; }
    public Package getPkg() { return pkg; }
    @Override
    public String toString() {
        return "LockerAssignment{" + "code='" + accessCode + '\'' +
                ", locker=" + locker.getLockerId() +
                ", package=" + pkg.getPackageId() + '}';
    }
}
