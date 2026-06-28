package za.ac.cput.controller;

import org.springframework.http.HttpStatus;
import za.ac.cput.domain.User;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Address;
import za.ac.cput.factory.UserFactory;
import za.ac.cput.repository.UserRepository;
import za.ac.cput.service.UserService;
import za.ac.cput.dto.LoginRequest;
import za.ac.cput.dto.UserDTO;
import za.ac.cput.dto.ContactDTO;
import za.ac.cput.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class UserController {
    private final UserService userService;
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserFactory userFactory, UserRepository userRepository) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            String roleStr = (request.getRole() == null || request.getRole().isBlank()) ? "CUSTOMER" : request.getRole();
            User.Role role = User.Role.valueOf(roleStr.toUpperCase());

            User user = userFactory.createUser(
                    request.getName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPassword(),
                    request.getNumber(),
                    role,
                    request.getStreet(),
                    request.getCity(),
                    request.getPostalCode(),
                    request.getCountry()
            );

            User savedUser = userService.registerUser(user);
            UserDTO dto = toDTO(savedUser);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = userRepository.findByContactEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        UserDTO dto = toDTO(user);
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return userService.getUserById(id)
            .map(user -> ResponseEntity.ok(toDTO(user)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody RegistrationRequest request) {
        try {
            User.Role role = User.Role.valueOf(request.getRole().toUpperCase());
            User updatedUser = userFactory.createUser(
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getNumber(),
                role,
                request.getStreet(),
                request.getCity(),
                request.getPostalCode(),
                request.getCountry()
            );
            User user = userService.updateUser(id, updatedUser);
            UserDTO dto = toDTO(user);
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    // Helper method to map User to UserDTO
    private UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole().name());
        if (user.getContact() != null) {
            Contact contact = user.getContact();
            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setId(contact.getId());
            contactDTO.setEmail(contact.getEmail());
            contactDTO.setNumber(contact.getNumber());
            dto.setContact(contactDTO);
        }
        if (user.getAddress() != null) {
            Address address = user.getAddress();
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setCity(address.getCity());
            addressDTO.setPostalCode(address.getPostalCode());
            addressDTO.setCountry(address.getCountry());
            dto.setAddress(addressDTO);
        }
        return dto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Request DTOs
    public static class RegistrationRequest {
        private String name;
        private String lastName;
        private String email;
        private String password;
        private String number;
        private String role;
        private String street;
        private String city;
        private String postalCode;
        private String country;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public String getStreet() { return street; }
        public void setStreet(String street) { this.street = street; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getPostalCode() { return postalCode; }
        public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
}