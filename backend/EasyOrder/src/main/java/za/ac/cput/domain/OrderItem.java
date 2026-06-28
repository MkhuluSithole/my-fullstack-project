package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;
    private double price;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderItem() {}

    private OrderItem(Builder builder) {
        this.id = builder.id;
        this.itemName = builder.itemName;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.order = builder.order;
        this.product = builder.product;
    }

    public static class Builder {
        private Long id;
        private String itemName;
        private int quantity;
        private double price;
    private Order order;
    private Product product;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder itemName(String itemName) { this.itemName = itemName; return this; }
        public Builder quantity(int quantity) { this.quantity = quantity; return this; }
        public Builder price(double price) { this.price = price; return this; }
    public Builder order(Order order) { this.order = order; return this; }
    public Builder product(Product product) { this.product = product; return this; }
        public OrderItem build() { return new OrderItem(this); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
