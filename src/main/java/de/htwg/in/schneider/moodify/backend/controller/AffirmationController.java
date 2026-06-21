package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.Affirmation;
import de.htwg.in.schneider.moodify.backend.repository.AffirmationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affirmations")
@CrossOrigin(origins = "http://localhost:5173")
public class AffirmationController {

    private final AffirmationRepository repo;

    public AffirmationController(AffirmationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Affirmation> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Affirmation create(@RequestBody Affirmation a) {
        return repo.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Affirmation update(@PathVariable Long id, @RequestBody Affirmation a) {
        return repo.findById(id).map(old -> {
            old.setText(a.getText());
            return repo.save(old);
        }).orElseThrow();
    }
}