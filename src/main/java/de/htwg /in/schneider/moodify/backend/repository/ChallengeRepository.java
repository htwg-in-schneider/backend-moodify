package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import de.htwg.in.schneider.moodify.backend.model.Category;
import java.util.List;

import de.htwg.in.schneider.moodify.backend.model.Challenge;

public interface ChallengeRepository extends JpaRepository <Challenge, Long> {

List<Challenge> findByNameContainingIgnoreCase(String name);

List<Challenge> findByCategory(Category category);

List<Challenge> findByNameContainingIgnoreCaseAndCategory(String name, Category category);

}