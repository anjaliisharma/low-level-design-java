package cor;

public class CreditCardPaymentHandler extends PaymentHandler{
    @Override
    public void handlePayment(double amount) {
        if(amount<=1000){
            System.out.println("Credit card processing payment");
        }
        else
            next.handlePayment(amount);
    }
}
