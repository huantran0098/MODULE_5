package backend.service;

import backend.models.User;

import java.util.Optional;

public interface IUserService {
    void save(User user);
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);}
