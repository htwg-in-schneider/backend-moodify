package de.htwg.in.schneider.moodify.backend.controller;

import de.htwg.in.schneider.moodify.backend.model.MoodQuestion;
import de.htwg.in.schneider.moodify.backend.repository.MoodQuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moodquiz")
@CrossOrigin(origins = "http://localhost:5173")
public class MoodQuizController {

    private final MoodQuestionRepository repo;

    public MoodQuizController(MoodQuestionRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<MoodQuestion> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public MoodQuestion create(@RequestBody MoodQuestion q) {
        q.setId(null);
        return repo.save(q);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public MoodQuestion update(@PathVariable Long id, @RequestBody MoodQuestion q) {
        return repo.findById(id)
                .map(old -> {
                    old.setText(q.getText());
                    old.setAnswers(q.getAnswers());
                    return repo.save(old);
                })
                .orElseThrow();
    }
}