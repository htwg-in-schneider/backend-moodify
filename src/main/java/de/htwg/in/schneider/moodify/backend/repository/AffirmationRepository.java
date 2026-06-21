package de.htwg.in.schneider.moodify.backend.repository;

import de.htwg.in.schneider.moodify.backend.model.Affirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffirmationRepository extends JpaRepository<Affirmation, Long> {
}