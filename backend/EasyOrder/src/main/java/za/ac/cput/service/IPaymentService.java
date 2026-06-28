package za.ac.cput.service;

import za.ac.cput.domain.Payment;
import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    Payment savePayment(Payment payment);
    Optional<Payment> getPaymentById(Long id);
    List<Payment> getPaymentsByOrder(Long orderId);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
}
