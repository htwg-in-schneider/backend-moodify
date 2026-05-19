package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Visionboard;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardRepository;

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
@RequestMapping("/api/visionboard")
public class VisionboardController {

    @Autowired
    private final VisionboardRepository repository;

    public VisionboardController(VisionboardRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Visionboard> getVisionboard() {
        return repository.findAll();
    }

    @PostMapping
    public Visionboard createVisionboard(@RequestBody Visionboard visionboard) {


       if (visionboard.getID() != null) {
        visionboard.setID(null);
       }

       Visionboard newVisionboard = repository.save(visionboard);

       return newVisionboard;

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
        vs.setImageUrls(visionboardDetails.getImageUrls());

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