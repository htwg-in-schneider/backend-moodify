package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.htwg.in.schneider.moodify.backend.model.Category;
import java.util.List;
import java.util.Optional;

import de.htwg.in.schneider.moodify.backend.model.Challenge;

public interface ChallengeRepository extends JpaRepository <Challenge, Long> {

List<Challenge> findByTitleContainingIgnoreCase(String title);

List<Challenge> findByCategory(Category category);

List<Challenge> findByTitleContainingIgnoreCaseAndCategory(String title, Category category);

boolean existsByTitle(String title);

Optional<Challenge> findByTitle(String title);

}