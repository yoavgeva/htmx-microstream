package org.example.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.example.repositories.UserRepository;

import java.util.Optional;

@ApplicationScoped
public class AuthenticationService {

    @ConfigProperty(name = "project.enryption.key")
    String encryptionKey;
    @Inject
    UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> {
                            Optional<String> decryptedPassword = AESUtil.decrypt(user.getPassword(), encryptionKey);
                            return decryptedPassword.map(s -> s.equals(password)).orElse(false);
                        }
                )
                .orElse(false);
    }
}
