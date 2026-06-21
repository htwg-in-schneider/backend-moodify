package de.htwg.in.schneider.moodify.backend.repository;

import de.htwg.in.schneider.moodify.backend.model.MoodQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodQuestionRepository extends JpaRepository<MoodQuestion, Long> {
}