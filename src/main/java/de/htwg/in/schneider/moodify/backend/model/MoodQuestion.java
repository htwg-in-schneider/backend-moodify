package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.*;

@Entity
public class MoodQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public MoodQuestion() {}

    public MoodQuestion(String text) {
        this.text = text;
    }

    public Long getId() { return id; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}