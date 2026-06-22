package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.MoodEntry;
import de.htwg.in.schneider.moodify.backend.repository.MoodRepository;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

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

    private final UserRepository userRepository;

    public MoodController(MoodRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

   
   @PostMapping
   public MoodEntry save(@RequestBody MoodEntry entry,
                      @AuthenticationPrincipal Jwt jwt) {

            entry.setUserId(jwt.getSubject());
            entry.setDate(LocalDate.now());

        return repository.save(entry);
    }

    
    @GetMapping("/me")
    public List<MoodEntry> getMyMoods(@AuthenticationPrincipal Jwt jwt) {
        return repository.findByUserId(jwt.getSubject());
    }

    @GetMapping("/latest")
    public ResponseEntity<MoodEntry> getLatestMood(@AuthenticationPrincipal Jwt jwt) {

        if (jwt == null || jwt.getSubject() == null) {
            return ResponseEntity.status(401).build();
        }

        String userId = jwt.getSubject();

        Optional<MoodEntry> latestMood =
            repository.findTopByUserIdOrderByDateDesc(userId);

        if (!latestMood.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(latestMood.get());
    }

}