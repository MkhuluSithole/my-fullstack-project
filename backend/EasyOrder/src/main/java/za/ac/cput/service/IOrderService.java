package za.ac.cput.service;

import za.ac.cput.domain.Order;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(Long id);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
    void deleteOrder(Long id);
}
