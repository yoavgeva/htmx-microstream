package org.example.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import one.microstream.storage.types.StorageManager;
import org.example.entities.RootEntity;
import org.example.entities.UserEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {
    @Inject
    RootEntity rootEntity;
    @Inject
    StorageManager storageManager;


    public List<UserEntity> getAllUsers() {
        return rootEntity.users();
    }

    public void addUser(UserEntity user) {
        findByEmail(user.getEmail()).ifPresent(existingUser -> {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        });
        rootEntity.users().add(user);
        storageManager.store(rootEntity.users());
    }

    public Optional<UserEntity> findByEmail(String email) {
        return rootEntity.users().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public int count() {
        return rootEntity.users().size();
    }
}
