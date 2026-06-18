package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Review;
import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.repository.ReviewRepository;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewRepository repository;
    private final ChallengeRepository challengeRepository;

    public ReviewController(
            ReviewRepository repository,
            ChallengeRepository challengeRepository) {

        this.repository = repository;
        this.challengeRepository = challengeRepository;
    }

    @GetMapping
    public List<Review> getReviews() {
        return repository.findAll();
    }

    @PostMapping("/{challengeId}")
    public ResponseEntity<Review> createReview(
            @PathVariable Long challengeId,
            @RequestBody Review review) {

        Optional<Challenge> opt = challengeRepository.findById(challengeId);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        review.setid(null);
        review.setChallenge(opt.get());

        Review newReview = repository.save(review);

        return ResponseEntity.ok(newReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody Review reviewDetails) {

        Optional<Review> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Review review = opt.get();

        review.setText(reviewDetails.getText());

        Review updatedReview = repository.save(review);

        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable Long id) {

        Optional<Review> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(opt.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {

        Optional<Review> opt = repository.findById(id);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}