package de.htwg.in.schneider.moodify.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);
    
    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

@GetMapping
public ResponseEntity<User> getProfile(@AuthenticationPrincipal Jwt jwt) {

    String oauthId = jwt.getSubject();

    LOGGER.info("getProfile called for: {}", oauthId);

    if (oauthId == null) {
        return ResponseEntity.badRequest().build();
    }

    return userRepository.findByOauthId(oauthId)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@PutMapping
public ResponseEntity<User> updateProfile(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody User userDetails) {

    String oauthId = jwt.getSubject();

    if (oauthId == null) {
        return ResponseEntity.badRequest().build();
    }

    return userRepository.findByOauthId(oauthId)
            .map(user -> {
                user.setName(userDetails.getName());
                user.setAddress(userDetails.getAddress());
                User updatedUser = userRepository.save(user);
                return ResponseEntity.ok(updatedUser);
            })
            .orElse(ResponseEntity.notFound().build());
}



}