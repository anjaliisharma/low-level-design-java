package state;

import enums.ATMStatus;
import model.Card;

public interface ATMState {
    void insertCard(Card card);
    void enterPin(String pin);
    void selectOption(String option);
    void dispenseCash(int amount);
    void ejectCard();
    ATMStatus getStatus();

}
