package za.ac.cput.domain;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String number;

    @OneToOne(mappedBy = "contact")
    @JsonIgnore
    private User user;

    public Contact() {}
    private Contact(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.number = builder.number;
        this.user = builder.user;
    }

    public static class Builder {
        private Long id;
        private String email;
        private String number;
        private User user;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder number(String number) { this.number = number; return this; }
        public Builder user(User user) { this.user = user; return this; }
        public Contact build() { return new Contact(this); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
