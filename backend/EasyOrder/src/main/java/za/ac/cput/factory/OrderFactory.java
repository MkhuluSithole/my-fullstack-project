package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.User;
import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.DeliveryMethod;
import za.ac.cput.domain.PaymentMethod;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class OrderFactory {
    public Order createOrder(User user, List<OrderItem> items, Payment payment, DeliveryMethod deliveryMethod, PaymentMethod paymentMethod, Double totalAmount, String status) {
        return new Order.Builder()
            .user(user)
            .items(items)
            .payment(payment)
            .deliveryMethod(deliveryMethod)
            .paymentMethod(paymentMethod)
            .totalAmount(totalAmount)
            .status(status)
            .orderDate(LocalDateTime.now())
            .build();
    }
}
