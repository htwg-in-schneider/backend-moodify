package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import de.htwg.in.schneider.moodify.backend.model.Role;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository repository;

    private final UserRepository userRepository;

    public UserController(UserRepository repository, UserRepository userRepository) {
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
    public List<User> getUsers() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User userDetails, @AuthenticationPrincipal Jwt jwt) {


        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

        Optional<User> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        User user = opt.get();

        user.setUsername(userDetails.getUsername());
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        user.setRole(userDetails.getRole());

        User updatedUser = repository.save(user);

        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {


        if (!userFromJwtIsAdmin(jwt)) {
            return ResponseEntity.status(403).build();
        }

    Optional<User> opt = repository.findById(id);

    if (!opt.isPresent()) {
        return ResponseEntity.notFound().build();
    }

    repository.delete(opt.get());

    return ResponseEntity.noContent().build();
 }
}