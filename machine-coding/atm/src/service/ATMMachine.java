package service;

import factory.ATMStateFactory;
import lombok.Getter;
import lombok.Setter;
import model.ATM;
import model.Card;
import repository.ATMRepository;
import state.ATMState;

@Getter
public class ATMMachine {
    private final ATM atm;
    private ATMState state;
    private final ATMRepository atmRepository;
    @Setter
    private Card currentCard;

    public ATMMachine(String atmId, ATMRepository atmRepository) {
        this.atmRepository = atmRepository;
        this.atm = atmRepository.getById(atmId)
                .orElseThrow(() -> new RuntimeException("ATM not found"));
        this.state = ATMStateFactory.getState(atm.getStatus(), this);
    }

    public void insertCard(Card card) {
        state.insertCard(card);
    }

    public void enterPin(String pin) {
        state.enterPin(pin);
    }

    public void selectOption(String option) {
        state.selectOption(option);
    }

    public void dispenseCash(int amount) {
        state.dispenseCash(amount);
    }

    public void ejectCard() {
        state.ejectCard();
    }

    public void setState(ATMState state) {
        this.state = state;
        this.atm.setStatus(state.getStatus());
        // persist the changes in db
    }
}
