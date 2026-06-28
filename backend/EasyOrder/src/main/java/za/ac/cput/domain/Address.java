package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String postalCode;
    private String country;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address() {}

    private Address(Builder builder) {
        this.id = builder.id;
        this.street = builder.street;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.user = builder.user;
    }

    public static class Builder {
        private Long id;
        private String street;
        private String city;
        private String postalCode;
        private String country;
        private User user;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder street(String street) { this.street = street; return this; }
        public Builder city(String city) { this.city = city; return this; }
        public Builder postalCode(String postalCode) { this.postalCode = postalCode; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Address build() { return new Address(this); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
