package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

@Entity
public class MoodQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ElementCollection
    private List<String> answers = new ArrayList<>();

    public MoodQuestion() {}

    public MoodQuestion(String text, List<String> answers) {
        this.text = text;
        this.answers = answers;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() { 
        return text; 
    }

    public void setText(String text) { 
        this.text = text; 
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoodQuestion moodquestion = (MoodQuestion) o;
        return id != null && id.equals(moodquestion.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "User{" +
            "id=" + id +
            ", text=" + text +
            '}';
    }
}