package proxy;

public class BankAccount implements Account{
    @Override
    public void withdraw() {
        System.out.println("withdrawn money");
    }

    @Override
    public void deposit() {
        System.out.println("money deposited");
    }
}
