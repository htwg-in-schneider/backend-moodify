package de.htwg.in.schneider.moodify.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @GetMapping
    public List<String> getMoods() {
        return List.of("Happy", "Sad", "Relaxed");
    }
}