package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.htwg.in.schneider.moodify.backend.model.Category;
import java.util.List;

import de.htwg.in.schneider.moodify.backend.model.Visionboard;

public interface VisionboardRepository extends JpaRepository <Visionboard, Long> {

    List<Visionboard> findByTitleContainingIgnoreCase(String title);

    List<Visionboard> findByCategory(Category category);

    List<Visionboard> findByTitleContainingIgnoreCaseAndCategory(String title, Category category);
}