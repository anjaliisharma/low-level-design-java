package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
    private String cardNumber;
    private String pin;
    private Account account;
}
