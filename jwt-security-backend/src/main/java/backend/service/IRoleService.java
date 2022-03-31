package backend.service;

import backend.models.ERole;
import backend.models.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(ERole name);

}
