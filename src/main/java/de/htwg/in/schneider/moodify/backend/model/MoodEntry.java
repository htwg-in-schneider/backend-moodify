package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String mood;
    private int score;
    private LocalDate date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoodEntry moodentry = (MoodEntry) o;
        return id != null && id.equals(moodentry.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "User{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", mood='" + mood + '\'' +
            ", score='" + score + '\'' +
            ", date=" + date +
            '}';
    }
}