package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.MoodEntry;
import de.htwg.in.schneider.moodify.backend.repository.MoodRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/mood")
@CrossOrigin(origins = "http://localhost:5173")
public class MoodController {

    private final MoodRepository repository;

    public MoodController(MoodRepository repository) {
        this.repository = repository;
    }

    // 💾 SPEICHERN
   @PostMapping
public MoodEntry save(@RequestBody MoodEntry entry,
                      @AuthenticationPrincipal Jwt jwt) {

    entry.setUserId(jwt.getSubject());
    entry.setDate(LocalDate.now());

    return repository.save(entry);
}

    // 📊 ALLE MOODS VOM USER
    @GetMapping("/me")
    public List<MoodEntry> getMyMoods(@AuthenticationPrincipal Jwt jwt) {
        return repository.findByUserId(jwt.getSubject());
    }
}