package com.amazonlocker;

import com.amazonlocker.enums.LockerSize;
import com.amazonlocker.exception.InvalidAccessCodeException;
import com.amazonlocker.exception.NoLockerAvailableException;
import com.amazonlocker.model.Customer;
import com.amazonlocker.model.Locker;
import com.amazonlocker.model.LockerAssignment;
import com.amazonlocker.model.LockerLocation;
import com.amazonlocker.model.Package;
import com.amazonlocker.service.LockerService;
import com.amazonlocker.strategy.BestFitAssignmentStrategy;

/**
 * Core interview demo: set up lockers, drop off a package, reject a wrong
 * code, complete pickup, and handle unavailable capacity.
 */
public class Main {

    public static void main(String[] args) {
        LockerService lockerService = new LockerService(new BestFitAssignmentStrategy());

        LockerLocation whiteField = new LockerLocation("LOC-1", "Whitefield Hub, Bengaluru");
        whiteField.addLocker(new Locker("S-1", LockerSize.SMALL));
        whiteField.addLocker(new Locker("S-2", LockerSize.SMALL));
        whiteField.addLocker(new Locker("M-1", LockerSize.MEDIUM));
        whiteField.addLocker(new Locker("L-1", LockerSize.LARGE));
        lockerService.registerLocation(whiteField);

        Customer suraj = new Customer("C-1", "Suraj");

        System.out.println("== Step 1: Drop off a SMALL package ==");
        Package pkg1 = new Package("PKG-1", "ORD-1", suraj, LockerSize.SMALL);
        LockerAssignment assignment1 = lockerService.dropOffPackage("LOC-1", pkg1);
        System.out.println("Assigned -> " + assignment1);
        System.out.println();

        System.out.println("== Step 2: Attempt pickup with the WRONG code ==");
        try {
            lockerService.pickUpPackage("LOC-1", assignment1.getLocker().getLockerId(), "000000");
        } catch (InvalidAccessCodeException e) {
            System.out.println("Rejected as expected: " + e.getMessage());
        }
        System.out.println();

        System.out.println("== Step 3: Pick up with the RIGHT code ==");
        Package pickedUp = lockerService.pickUpPackage(
                "LOC-1", assignment1.getLocker().getLockerId(), assignment1.getAccessCode());
        System.out.println("Picked up -> " + pickedUp + " | locker now: "
                + assignment1.getLocker().getStatus());
        System.out.println();

        System.out.println("== Step 4: Exhaust LARGE locker capacity ==");
        Package pkg2 = new Package("PKG-2", "ORD-2", suraj, LockerSize.LARGE);
        LockerAssignment assignment2 = lockerService.dropOffPackage("LOC-1", pkg2);
        System.out.println("Assigned -> " + assignment2);
        try {
            Package pkg3 = new Package("PKG-3", "ORD-3", suraj, LockerSize.LARGE);
            lockerService.dropOffPackage("LOC-1", pkg3);
        } catch (NoLockerAvailableException e) {
            System.out.println("Rejected as expected: " + e.getMessage());
        }
    }
}
