package za.ac.cput.factory;

import za.ac.cput.domain.Payment;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.PaymentMethod;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class PaymentFactory {
    public Payment createPayment(PaymentMethod paymentMethod, String cardNumber, String pin, Double amount, String status, User user, Order order) {
        return new Payment.Builder()
            .paymentMethod(paymentMethod)
            .cardNumber(cardNumber)
            .pin(pin)
            .amount(amount)
            .status(status)
            .paymentDate(LocalDateTime.now())
            .user(user)
            .order(order)
            .build();
    }
}
