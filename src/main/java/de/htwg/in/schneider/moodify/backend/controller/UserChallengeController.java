package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.UserChallenge;
import de.htwg.in.schneider.moodify.backend.repository.UserChallengeRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user-challenges")
public class UserChallengeController {

    private final UserChallengeRepository repository;

    public UserChallengeController(UserChallengeRepository repository) {
        this.repository = repository;
    }

    
    @PostMapping
public UserChallenge save(
        @RequestBody UserChallenge uc,
        @AuthenticationPrincipal Jwt jwt
) {
    String userId = jwt.getSubject();

    System.out.println("=== SAVE DEBUG ===");
    System.out.println("USER ID: " + userId);
    System.out.println("CHALLENGE ID: " + uc.getChallengeId());

    uc.setUserId(userId);

    UserChallenge saved = repository.save(uc);

    System.out.println("SAVED ID: " + saved.getId());

    return saved;
}

    
  @GetMapping("/me")
public List<UserChallenge> getMyChallenges(
        @AuthenticationPrincipal Jwt jwt
) {

    System.out.println("ME ENDPOINT CALLED");

    String userId = jwt.getSubject();

    System.out.println("USER ID = " + userId);

    return repository.findByUserId(userId);
}
}