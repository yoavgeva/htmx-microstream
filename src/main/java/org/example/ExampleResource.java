package org.example;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.entities.UserEntity;
import org.example.repositories.UserRepository;

@Path("/hello")
public class ExampleResource {

    @Inject
    UserRepository userRepository;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        userRepository.addUser(new UserEntity());
        userRepository.getAllUsers().forEach(user -> Log.info(user.toString()));
        return "Hello from Quarkus REST";
    }
}
