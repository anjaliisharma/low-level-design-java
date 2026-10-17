package proxy;

public class ATM implements Account{
    @Override
    public void withdraw() {
        //Access using actual object. You can also have checks on withdraw to achieve authentication or pin is correct or not.
        BankAccount bankAccount = new BankAccount();
        bankAccount.withdraw();
    }

    @Override
    public void deposit() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit();
    }
}
