package cor;

/**
 * Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers.
 * Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
 *
 * The Chain of Responsibility pattern allows a number of classes to attempt to handle a request independently.
 *
 * Reference: https://refactoring.guru/design-patterns/chain-of-responsibility
 **/
public class ChainOfResponsibilityApplication {
    public static void main(String[] args){
        PaypalPaymentHandler paypal = new PaypalPaymentHandler();
        CreditCardPaymentHandler credit =new CreditCardPaymentHandler();
        BankPaymentHandler bank = new BankPaymentHandler();

        bank.setNext(credit);
        credit.setNext(paypal);

        bank.handlePayment(400);
        bank.handlePayment(900);
        bank.handlePayment(2000);
        bank.handlePayment(7000);
    }

}
