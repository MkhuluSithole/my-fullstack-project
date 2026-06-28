package za.ac.cput.repository;

import za.ac.cput.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    java.util.List<Payment> findByUserId(Long userId);
    java.util.List<Payment> findByOrderId(Long orderId);
}
