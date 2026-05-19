package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Product;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.repository.ProductRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Product> getProducts() {
        return repository.findAll();
    }


    @PostMapping
    public Product createChallenge(@RequestBody Product challenge) {


       if (challenge.getID() != null) {
        challenge.setID(null);
       }

       Product newChallenge = repository.save(challenge);

       return newChallenge;

    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateChallenge(

        @PathVariable Long id,
        @RequestBody Product challengeDetails) {

        Optional<Product> opt = repository.findById(id);

        if (!opt.isPresent()) {
          return ResponseEntity.notFound().build();
        }

        Product challenge = opt.get();

        challenge.setTitle(challengeDetails.getTitle());
        challenge.setCategory(challengeDetails.getCategory());
        challenge.setDescription(challengeDetails.getDescription());

        Product updatedChallenge = repository.save(challenge);

        return ResponseEntity.ok(updatedChallenge);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteChallenge(@PathVariable Long id) {

       Optional<Product> opt = repository.findById(id);

       if (!opt.isPresent()) {
          return ResponseEntity.notFound().build();
       }

       repository.delete(opt.get());

       return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getChallengeById(@PathVariable Long id) {

      Optional<Product> opt = repository.findById(id);

      if (opt.isPresent()) {
          return ResponseEntity.ok(opt.get());
      } else {
          return ResponseEntity.notFound().build();
     }
    }


}