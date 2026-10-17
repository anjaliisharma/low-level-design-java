package cor;

public class PaypalPaymentHandler extends PaymentHandler{
    @Override
    public void handlePayment(double amount) {
        if(amount<=2000){
            System.out.println("Paypal processing payment");
        }
        else
            next.handlePayment(amount);
    }
}
