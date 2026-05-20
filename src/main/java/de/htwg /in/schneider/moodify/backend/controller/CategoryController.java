package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Category;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @GetMapping
    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }


    @GetMapping("/translation")
    public Map<String, String> getAllCategories() {

        return Arrays.stream(Category.values())
                .collect(Collectors.toMap(
                        Category::name,
                        Category::getName
                ));
    }
}