package za.ac.cput.factory;

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderItemFactory {
    public OrderItem createOrderItem(String itemName, int quantity, double price, Order order, Product product) {
        return new OrderItem.Builder()
            .itemName(itemName)
            .quantity(quantity)
            .price(price)
            .order(order)
            .product(product)
            .build();
    }
}
