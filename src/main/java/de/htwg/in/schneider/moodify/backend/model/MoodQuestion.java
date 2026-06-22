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

    public Long getId() { 
        return id; 
    }

    public String getText() { 
        return text; 
    }

    public void setText(String text) { 
        this.text = text; 
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