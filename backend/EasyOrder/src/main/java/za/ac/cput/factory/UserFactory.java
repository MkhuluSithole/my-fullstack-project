package za.ac.cput.factory;

import za.ac.cput.domain.User;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User createUser(String name, String lastName, String email,
                           String password, String number, User.Role role,
                           String street, String city, String postalCode, String country) {

        Contact contact = new Contact.Builder()
                .email(email)
                .number(number)
                .build();

        Address address = new Address.Builder()
                .street(street)
                .city(city)
                .postalCode(postalCode)
                .country(country)
                .build();

        return new User.Builder()
                .name(name)
                .lastName(lastName)
                .password(password) // ✅ ensure password is set
                .role(role)
                .contact(contact)
                .address(address)
                .build();
    }
}
