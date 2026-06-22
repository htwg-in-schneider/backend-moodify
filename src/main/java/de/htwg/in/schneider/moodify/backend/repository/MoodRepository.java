package de.htwg.in.schneider.moodify.backend.repository;

import de.htwg.in.schneider.moodify.backend.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import de.htwg.in.schneider.moodify.backend.model.User;
import java.util.Optional;

import java.util.List;

public interface MoodRepository extends JpaRepository<MoodEntry, Long> {

    List<MoodEntry> findByUserId(String userId);

    Optional<MoodEntry> findTopByUserIdOrderByDateDesc(String userId);
}