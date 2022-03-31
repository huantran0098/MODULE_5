package backend.service;

import backend.model.User;

import java.util.Optional;

public interface IUserService {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    void delete(Long id);
    void save(User user);
    Boolean existsByEmailOrUsername(String email, String username);

    boolean checkLogin(User user);
}
