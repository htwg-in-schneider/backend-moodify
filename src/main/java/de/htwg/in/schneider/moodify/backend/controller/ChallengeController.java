package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    private final ChallengeRepository repository;

    public ChallengeController(ChallengeRepository repository) {
        this.repository = repository;
    }

    // ✅ GET ALL + FILTER (PUBLIC ODER TOKEN-GESCHÜTZT - je nach Security Config)
    @GetMapping
    public List<Challenge> getChallenges(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Category category
    ) {

        if (title != null && category != null) {
            return repository.findByTitleContainingIgnoreCaseAndCategory(title, category);
        } else if (title != null) {
            return repository.findByTitleContainingIgnoreCase(title);
        } else if (category != null) {
            return repository.findByCategory(category);
        } else {
            return repository.findAll();
        }
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {

        Optional<Challenge> opt = repository.findById(id);

        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ CREATE
   @PostMapping
public Challenge createChallenge(@RequestBody Challenge challenge) {
    return repository.save(challenge);
}

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(
            @PathVariable Long id,
            @RequestBody Challenge challengeDetails
    ) {

        Optional<Challenge> opt = repository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Challenge challenge = opt.get();

        challenge.setTitle(challengeDetails.getTitle());
        challenge.setCategory(challengeDetails.getCategory());
        challenge.setDescription(challengeDetails.getDescription());
        challenge.setDifficulty(challengeDetails.getDifficulty());

        return ResponseEntity.ok(repository.save(challenge));
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}