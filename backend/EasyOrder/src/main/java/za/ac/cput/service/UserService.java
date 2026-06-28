package za.ac.cput.service;

import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(User user) throws IllegalArgumentException {
        String email = user.getContact() != null ? user.getContact().getEmail() : null;
        String number = user.getContact() != null ? user.getContact().getNumber() : null;
        if (email != null && userRepository.existsByContactEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (number != null && userRepository.existsByContactNumberAndRole(number, user.getRole())) {
            throw new IllegalArgumentException("Number already exists for this role");
        }
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        return userRepository.findByContactEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    }


    public Optional<User> getUserById(Long id) {
            return userRepository.findById(id);
        }

        public User updateUser(Long id, User updatedUser) {
            return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(updatedUser.getName());
                    existingUser.setLastName(updatedUser.getLastName());
                    // Only update password if provided
                    if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
                        existingUser.setPassword(updatedUser.getPassword());
                    }
                    existingUser.setRole(updatedUser.getRole());
                    existingUser.setContact(updatedUser.getContact());
                    existingUser.setAddress(updatedUser.getAddress());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        public void deleteUser(Long id) {
            if (!userRepository.existsById(id)) {
                throw new IllegalArgumentException("User not found");
            }
            userRepository.deleteById(id);
        }
}