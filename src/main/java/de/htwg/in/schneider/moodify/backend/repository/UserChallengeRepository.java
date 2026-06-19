package de.htwg.in.schneider.moodify.backend.repository;

import de.htwg.in.schneider.moodify.backend.model.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChallengeRepository
        extends JpaRepository<UserChallenge, Long> {

    List<UserChallenge> findByUserId(String userId);
}