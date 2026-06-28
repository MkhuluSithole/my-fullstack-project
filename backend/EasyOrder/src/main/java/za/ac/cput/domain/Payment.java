package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String cardNumber; // null for cash payments
    private String pin; // null for cash payments
    private Double amount;
    private String status; // "PENDING", "COMPLETED", "FAILED"
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Payment() {
        this.paymentDate = LocalDateTime.now();
        this.status = "PENDING";
    }

    private Payment(Builder builder) {
        this.id = builder.id;
        this.paymentMethod = builder.paymentMethod;
        this.cardNumber = builder.cardNumber;
        this.pin = builder.pin;
        this.amount = builder.amount;
        this.status = builder.status;
        this.paymentDate = builder.paymentDate;
        this.user = builder.user;
        this.order = builder.order;
    }

    public static class Builder {
        private Long id;
    private PaymentMethod paymentMethod;
        private String cardNumber;
        private String pin;
        private Double amount;
        private String status = "PENDING";
        private LocalDateTime paymentDate = LocalDateTime.now();
        private User user;
        private Order order;

        public Builder id(Long id) { this.id = id; return this; }
    public Builder paymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; return this; }
        public Builder cardNumber(String cardNumber) { this.cardNumber = cardNumber; return this; }
        public Builder pin(String pin) { this.pin = pin; return this; }
        public Builder amount(Double amount) { this.amount = amount; return this; }
        public Builder status(String status) { this.status = status; return this; }
        public Builder paymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Builder order(Order order) { this.order = order; return this; }
        public Payment build() { return new Payment(this); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}
