package za.ac.cput.service;

import za.ac.cput.domain.OrderItem;
import java.util.List;
import java.util.Optional;

public interface IOrderItemService {
    OrderItem saveOrderItem(OrderItem orderItem);
    Optional<OrderItem> getOrderItemById(Long id);
    List<OrderItem> getOrderItemsByOrder(Long orderId);
    List<OrderItem> getAllOrderItems();
    void deleteOrderItem(Long id);
}
