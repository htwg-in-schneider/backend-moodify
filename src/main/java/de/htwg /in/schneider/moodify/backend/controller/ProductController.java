package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Product;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final List<Product> challenges = new ArrayList<>();

    public ProductController() {
        challenges.add(new Product("Drink Water", "Trinke heute 2 Liter Wasser"));
    }

    @GetMapping
    public List<Product> getProducts() {
        return challenges;
    }

    @PostMapping
    public Product addProducts(@RequestBody Product product) {
    challenges.add(product);
    return product;
}
}