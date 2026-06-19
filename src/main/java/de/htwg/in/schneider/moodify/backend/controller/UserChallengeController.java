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

    // 🔥 SPEICHERN (User kommt automatisch aus Auth0 Token)
    @PostMapping
    public UserChallenge save(
            @RequestBody UserChallenge uc,
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaim("sub");
        uc.setUserId(userId);

        return repository.save(uc);
    }

    // 🔥 USER SPECIFIC DATA (/me Endpoint)
    @GetMapping("/me")
    public List<UserChallenge> getMyChallenges(
            @AuthenticationPrincipal Jwt jwt
    ) {
        String userId = jwt.getClaim("sub");

        return repository.findByUserId(userId);
    }
}