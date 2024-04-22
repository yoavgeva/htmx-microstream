package org.example.entities;

import one.microstream.integrations.quarkus.types.Storage;

import java.util.ArrayList;
import java.util.List;

@Storage
public class RootEntity {
    List<UserEntity> users = new ArrayList<>();

    public List<UserEntity> users() {
        return users;
    }
}
