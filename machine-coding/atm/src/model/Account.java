package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Account {
    private String accountNumber;
    @Setter private double balance;
}
