package de.htwg.in.schneider.moodify.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.htwg.in.schneider.moodify.backend.model.Visionboard;

public interface VisionboardRepository extends JpaRepository <Visionboard, Long> {
}