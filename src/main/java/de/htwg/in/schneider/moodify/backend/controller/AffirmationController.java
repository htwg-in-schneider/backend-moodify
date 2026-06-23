package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Affirmation;
import de.htwg.in.schneider.moodify.backend.repository.AffirmationRepository;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import de.htwg.in.schneider.moodify.backend.model.Role;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/api/affirmations")
@CrossOrigin(origins = "http://localhost:5173")
public class AffirmationController {

    private final AffirmationRepository repo;

    private final UserRepository userRepository;

    public AffirmationController(AffirmationRepository repo, UserRepository userRepository) {
        this.repo = repo;
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
    public List<Affirmation> getAll() {
        
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Affirmation> create(@RequestBody Affirmation a, @AuthenticationPrincipal Jwt jwt) {

        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(repo.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {

      if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Affirmation> update(@PathVariable Long id, @RequestBody Affirmation a, @AuthenticationPrincipal Jwt jwt) {

        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        return repo.findById(id).map(old -> {
            old.setText(a.getText());
            return ResponseEntity.ok(repo.save(old));
        }).orElse(ResponseEntity.notFound().build());
    }
}