package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.UserChallenge;
import de.htwg.in.schneider.moodify.backend.repository.UserChallengeRepository;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import de.htwg.in.schneider.moodify.backend.model.Role;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user-challenges")
public class UserChallengeController {

    private final UserChallengeRepository repository;

    private final UserRepository userRepository;

    public UserChallengeController(UserChallengeRepository repository, UserRepository userRepository) {
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