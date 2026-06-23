package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.MoodQuestion;
import de.htwg.in.schneider.moodify.backend.repository.MoodQuestionRepository;
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
@RequestMapping("/api/moodquiz")
@CrossOrigin(origins = "http://localhost:5173")
public class MoodQuizController {

    private final MoodQuestionRepository repo;

    private final UserRepository userRepository;

    public MoodQuizController(MoodQuestionRepository repo, UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<MoodQuestion> getAll() {
        return repo.findAll();
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

    @PostMapping
    public ResponseEntity<MoodQuestion> create(@RequestBody MoodQuestion q, @AuthenticationPrincipal Jwt jwt) {


        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        q.setId(null);
        return ResponseEntity.ok(repo.save(q));
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
    public ResponseEntity<MoodQuestion> update(@PathVariable Long id, @RequestBody MoodQuestion q, @AuthenticationPrincipal Jwt jwt) {

        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }
        
        return repo.findById(id)
                .map(old -> {
                    old.setText(q.getText());
                    old.setAnswers(q.getAnswers());
                    return ResponseEntity.ok(repo.save(old));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}