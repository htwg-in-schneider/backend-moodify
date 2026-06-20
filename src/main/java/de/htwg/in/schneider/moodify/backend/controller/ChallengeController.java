package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.model.Role;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    private final ChallengeRepository repository;
    private final UserRepository userRepository;

    public ChallengeController(ChallengeRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    private boolean userFromJwtIsAdmin(Jwt jwt) {


     if (jwt == null || jwt.getSubject() == null) {
        return false;
     }
    
     Optional<User> user = userRepository.findByOauthId(jwt.getSubject());

        if (!user.isPresent() || user.get().getRole() != Role.ADMIN) {

            return false;
        }

        return true;

    }

    
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

    
    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {

        Optional<Challenge> opt = repository.findById(id);

        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {


        if (challenge.getid() != null) {
            return ResponseEntity.badRequest().build();
        }

        if (challenge.getTitle() == null || challenge.getTitle().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (challenge.getDescription() == null || challenge.getDescription().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (challenge.getCategory() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (challenge.getDifficulty() == null) {
            return ResponseEntity.badRequest().build();
        }


        Challenge newChallenge = repository.save(challenge);
        return ResponseEntity.ok(newChallenge);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id,
            @RequestBody Challenge challengeDetails) 
    
    {

        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

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

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallenge(@AuthenticationPrincipal Jwt jwt, @PathVariable Long id) {

        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}