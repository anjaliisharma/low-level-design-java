package com.amazonlocker.model;

import com.amazonlocker.enums.LockerSize;
import com.amazonlocker.enums.PackageStatus;

/**
 * A parcel awaiting delivery to or pickup from a locker.
 */
public class Package {
    private final String packageId;
    private final String orderId;
    private final Customer customer;
    private final LockerSize size;
    private PackageStatus status;

    public Package(String packageId, String orderId, Customer customer, LockerSize size) {
        this.packageId = packageId;
        this.orderId = orderId;
        this.customer = customer;
        this.size = size;
        this.status = PackageStatus.AWAITING_DROPOFF;
    }

    public String getPackageId() { return packageId; }
    public String getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public LockerSize getSize() { return size; }
    public PackageStatus getStatus() { return status; }
    public void setStatus(PackageStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Package{" + "id='" + packageId + '\'' + ", size=" + size + ", status=" + status + '}';
    }
}
