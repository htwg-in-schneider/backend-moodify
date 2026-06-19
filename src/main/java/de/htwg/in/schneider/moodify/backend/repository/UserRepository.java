package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.htwg.in.schneider.moodify.backend.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthId(String oauthId);
    Optional<User> findByEmail(String email);
}