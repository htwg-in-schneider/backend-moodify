package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Visionboard;
import de.htwg.in.schneider.moodify.backend.model.Category;
import de.htwg.in.schneider.moodify.backend.repository.VisionboardRepository;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


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
    public Visionboard addVisionboard(@RequestBody Visionboard visionboard) {
        return repository.save(visionboard);
    }
}