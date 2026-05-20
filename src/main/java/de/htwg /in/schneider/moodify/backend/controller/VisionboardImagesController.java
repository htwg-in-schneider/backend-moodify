package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.VisionboardImages;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardImagesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/image")
public class VisionboardImagesController {

    @Autowired
    private final VisionboardImagesRepository repository;

    public VisionboardImagesController(VisionboardImagesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<VisionboardImages> getImages() {
        return repository.findAll();
    }

    @PostMapping
    public VisionboardImages createImage(@RequestBody VisionboardImages image) {

        if (image.getId() != null) {
            image.setId(null);
        }

        VisionboardImages newImage = repository.save(image);

        return newImage;
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisionboardImages> updateImage(
            @PathVariable Long id,
            @RequestBody VisionboardImages imageDetails) {

        Optional<VisionboardImages> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        VisionboardImages image = opt.get();

        image.setImageUrl(imageDetails.getImageUrl());
        image.setXPosition(imageDetails.getXPosition());
        image.setYPosition(imageDetails.getYPosition());
        image.setWidth(imageDetails.getWidth());
        image.setHeight(imageDetails.getHeight());
        image.setVisionboard(imageDetails.getVisionboard());

        VisionboardImages updatedImage = repository.save(image);

        return ResponseEntity.ok(updatedImage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteImage(@PathVariable Long id) {

        Optional<VisionboardImages> opt = repository.findById(id);

        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(opt.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisionboardImages> getImageById(@PathVariable Long id) {

        Optional<VisionboardImages> opt = repository.findById(id);

        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}