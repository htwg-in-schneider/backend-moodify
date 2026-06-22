package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.*;

@Entity
public class Affirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    public Affirmation() {}

    public Affirmation(String text) {
        this.text = text;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Affirmation affirmation = (Affirmation) o;
        return id != null && id.equals(affirmation.id);
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