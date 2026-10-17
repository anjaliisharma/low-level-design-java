Each File, Exactly What It Does

Entry point

[Main.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\Main.java)	“This is a demonstration program. It configures lockers, drops off a package, rejects a wrong code, completes a valid pickup, and shows the no-large-locker-available case.”



Model classes

[Customer.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\model\\Customer.java)	Stores the customer ID and name. A customer owns packages.

[Package.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\model\\Package.java)	Represents a parcel. It has ID, order ID, customer, required locker size, and package lifecycle state.

[Locker.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\model\\Locker.java)	Represents one physical compartment. It knows its size, availability, and currently stored package. assignPackage() occupies it; release() makes it available again.

[LockerLocation.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\model\\LockerLocation.java)	Represents a physical locker bank. It owns many lockers and exposes ways to find a locker or available lockers.

[LockerAssignment.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\model\\LockerAssignment.java)	Represents a successful drop-off. It connects the generated pickup code to one package and one locker.



Enums
File	Meaning

[LockerSize.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\enums\\LockerSize.java)	SMALL, MEDIUM, LARGE. canFit() checks whether the locker is large enough for a package.

[LockerStatus.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\enums\\LockerStatus.java)	AVAILABLE or OCCUPIED. This is the physical locker state.

[PackageStatus.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\enums\\PackageStatus.java)	AWAITING_DROPOFF, IN_LOCKER, or PICKED_UP. This is the parcel lifecycle.



Service and strategy
File	What you say

[LockerService.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\service\\LockerService.java)	“This is the application service and workflow coordinator. It manages locations and active assignments, and owns drop-off and pickup use cases.”

[LockerAssignmentStrategy.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\strategy\\LockerAssignmentStrategy.java)	Defines the contract for selecting a locker. The service depends on this interface, not a concrete selection algorithm.

[BestFitAssignmentStrategy.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\strategy\\BestFitAssignmentStrategy.java)	Implements the current allocation policy: choose the smallest available locker that fits the package.

[AccessCodeGenerator.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\util\\AccessCodeGenerator.java)	Generates six-digit numeric pickup codes using SecureRandom. LockerService additionally checks that an active assignment does not already use the generated code.


Exceptions

[LockerNotFoundException.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\exception\\LockerNotFoundException.java)	Requested location or locker does not exist.

[NoLockerAvailableException.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\exception\\NoLockerAvailableException.java)	No free locker is large enough for the package.

[InvalidAccessCodeException.java](C:\\Users\\anjal\\OneDrive\\Desktop\\Switch Preparation\\LLD\\amazonlocker\\exception\\InvalidAccessCodeException.java)	Code does not exist or does not belong to the selected locker.