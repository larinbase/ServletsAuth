package ru.itis.servletsauth.repositories;

import ru.itis.servletsauth.dto.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudRepository<T> {
    void save(T model);
    List<T> getAll();
    void delete();
    Optional<User> findById(UUID id);
    Optional<User> findByName(String name);
}
