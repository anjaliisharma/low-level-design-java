package model;

import enums.ATMStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ATM {
    private String id;
    @Setter private ATMStatus status;
    @Setter private double cashAvailable;
    @Setter private int twoThousandCount;
    @Setter private int fiveHundredCount;
    @Setter private int oneHundredCount;

    public ATM(String id, int twoThousandCount, int fiveHundredCount, int oneHundredCount) {
        this.oneHundredCount = oneHundredCount;
        this.fiveHundredCount = fiveHundredCount;
        this.twoThousandCount = twoThousandCount;
        this.cashAvailable = 2000*twoThousandCount+100*oneHundredCount+500*fiveHundredCount;
        this.status = ATMStatus.IDLE;
        this.id = id;
    }
}