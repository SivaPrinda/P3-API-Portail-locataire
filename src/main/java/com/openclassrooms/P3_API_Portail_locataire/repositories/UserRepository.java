package com.openclassrooms.P3_API_Portail_locataire.repositories;

import com.openclassrooms.P3_API_Portail_locataire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
