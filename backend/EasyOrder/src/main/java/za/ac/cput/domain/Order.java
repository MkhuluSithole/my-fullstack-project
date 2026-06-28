package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;
    private String status; // "PENDING", "PREPARING", "READY", "COMPLETED"

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    private Order(Builder builder) {
        this.id = builder.id;
        this.orderDate = builder.orderDate;
        this.status = builder.status;
        this.deliveryMethod = builder.deliveryMethod;
        this.paymentMethod = builder.paymentMethod;
        this.totalAmount = builder.totalAmount;
        this.user = builder.user;
        this.items = builder.items;
        this.payment = builder.payment;
    }

    public static class Builder {
        private Long id;
        private LocalDateTime orderDate = LocalDateTime.now();
        private String status = "PENDING";
        private DeliveryMethod deliveryMethod;
        private PaymentMethod paymentMethod;
        private Double totalAmount;
        private User user;
        private List<OrderItem> items;
        private Payment payment;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder orderDate(LocalDateTime orderDate) { this.orderDate = orderDate; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder deliveryMethod(DeliveryMethod deliveryMethod) { this.deliveryMethod = deliveryMethod; return this; }
        public Builder paymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; return this; }
        public Builder totalAmount(Double totalAmount) { this.totalAmount = totalAmount; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder items(List<OrderItem> items) { this.items = items; return this; }
        public Builder payment(Payment payment) { this.payment = payment; return this; }
        public Order build() { return new Order(this); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }
}
