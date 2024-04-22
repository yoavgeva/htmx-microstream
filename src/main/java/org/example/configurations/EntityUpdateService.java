package org.example.configurations;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import one.microstream.storage.types.StorageManager;
import org.example.entities.RootEntity;
import org.example.entities.UserEntity;

@ApplicationScoped
public class EntityUpdateService {
    @Inject
    StorageManager storageManager;

    @Inject
    RootEntity rootEntity;

    void updateEntities(@Observes StartupEvent event) {
        Log.info("Updating entities");
        updateUsers();
    }

    void updateUsers() {
        for (UserEntity user : rootEntity.users()) {
            if (user.getAddress() == null) {
                user.setAddress("default address");
            }
            storageManager.store(rootEntity.users());

        }
    }
}
