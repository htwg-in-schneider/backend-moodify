package de.htwg.in.schneider.moodify.backend.repository;

import de.htwg.in.schneider.moodify.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByChallengeId(Long challengeId);
}