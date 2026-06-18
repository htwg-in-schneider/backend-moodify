package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    @JsonIgnoreProperties("reviews")
    private Challenge challenge;


    public Review() {
    }

    public Review(Long id, String text, Challenge challenge) {
        this.id = id;
        this.text = text;
        this.challenge = challenge;
    }


    public Long getid() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    } 


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id != null && id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "Challenge{" +
            "id=" + id +
            ", text='" + text + '\'' +
            ", challenge=" + challenge +
            '}';
    }

}