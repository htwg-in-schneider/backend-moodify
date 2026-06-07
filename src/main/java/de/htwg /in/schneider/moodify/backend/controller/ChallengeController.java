package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Challenge;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.repository.ChallengeRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api/challenge")
public class ChallengeController {

    private final ChallengeRepository repository;

    public ChallengeController(ChallengeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Challenge> getChallenges(
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
    public Challenge createChallenge(@RequestBody Challenge challenge) {


       if (challenge.getid() != null) {
        challenge.setid(null);
       }

       Challenge newChallenge = repository.save(challenge);

       return newChallenge;

    }


    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(

        @PathVariable Long id,
        @RequestBody Challenge challengeDetails) {

        Optional<Challenge> opt = repository.findById(id);

        if (!opt.isPresent()) {
          return ResponseEntity.notFound().build();
        }

        Challenge challenge = opt.get();

        challenge.setTitle(challengeDetails.getTitle());
        challenge.setCategory(challengeDetails.getCategory());
        challenge.setDescription(challengeDetails.getDescription());
        challenge.setSchwierigkeitsgrad(challengeDetails.getSchwierigkeitsgrad());

        Challenge updatedChallenge = repository.save(challenge);

        return ResponseEntity.ok(updatedChallenge);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChallenge(@PathVariable Long id) {

       Optional<Challenge> opt = repository.findById(id);

       if (!opt.isPresent()) {
          return ResponseEntity.notFound().build();
       }

       repository.delete(opt.get());

       return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {

      Optional<Challenge> opt = repository.findById(id);

      if (opt.isPresent()) {
          return ResponseEntity.ok(opt.get());
      } else {
          return ResponseEntity.notFound().build();
     }
    }


}