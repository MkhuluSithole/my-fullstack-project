package za.ac.cput.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    @Lob
    private byte[] imageBlob;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    private boolean available = true;

    public Product() {}

    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
    this.imageBlob = builder.imageBlob;
        this.category = builder.category;
        this.available = builder.available;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private double price;
    private byte[] imageBlob;
        private Category category;
        private boolean available = true;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder price(double price) { this.price = price; return this; }
    public Builder imageBlob(byte[] imageBlob) { this.imageBlob = imageBlob; return this; }
        public Builder category(Category category) { this.category = category; return this; }
        public Builder available(boolean available) { this.available = available; return this; }
        public Product build() { return new Product(this); }
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public byte[] getImageBlob() { return imageBlob; }
    public void setImageBlob(byte[] imageBlob) { this.imageBlob = imageBlob; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
