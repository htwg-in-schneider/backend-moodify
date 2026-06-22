package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.model.Visionboard;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import de.htwg.in.schneider.moodify.backend.model.User;
import de.htwg.in.schneider.moodify.backend.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visionboard")
public class VisionboardController {

    private final VisionboardRepository repository;

    private final UserRepository userRepository;

    public VisionboardController(VisionboardRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Visionboard> getVisionboard(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category) {

        if (name != null && category != null) {
            return repository.findByTitleContainingIgnoreCaseAndCategory(name, category);

        } else if (name != null) {
            return repository.findByTitleContainingIgnoreCase(name);

        } else if (category != null) {
            return repository.findByCategory(category);

        } else {
            return repository.findAll();
        }
    }

    @PostMapping
    public ResponseEntity<Visionboard> createVisionboard(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody Visionboard visionboard) {

        if (jwt == null || jwt.getSubject() == null) {
            return ResponseEntity.status(401).build();
        }

        Optional<User> userOpt = userRepository.findByOauthId(jwt.getSubject());

        if (!userOpt.isPresent()) {
            return ResponseEntity.status(404).build();
        }

        visionboard.setID(null);
        visionboard.setUser(userOpt.get());

        if (visionboard.getImages() != null) {
            visionboard.getImages().forEach(image -> {
                image.setId(null);
                image.setVisionboard(visionboard);
            });
        }

        return ResponseEntity.ok(repository.save(visionboard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visionboard> updateVisionboard(
            @PathVariable Long id,
            @RequestBody Visionboard visionboardDetails) {

        Optional<Visionboard> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Visionboard vs = opt.get();

        vs.setTitle(visionboardDetails.getTitle());
        vs.setCategory(visionboardDetails.getCategory());
        vs.setCreatedAt(visionboardDetails.getCreatedAt());

        if (visionboardDetails.getImages() != null) {

            visionboardDetails.getImages().forEach(image -> {
                image.setVisionboard(vs);
            });

            vs.setImages(visionboardDetails.getImages());
        }

        Visionboard updatedVisionboard = repository.save(vs);

        return ResponseEntity.ok(updatedVisionboard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVisionboard(@PathVariable Long id) {

        Optional<Visionboard> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(opt.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visionboard> getVisionboardById(@PathVariable Long id) {

        Optional<Visionboard> opt = repository.findById(id);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



