package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.*;

@Entity
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;   // Auth0 User
    private Long challengeId;

    private boolean finished;

    private String mood;

    private String review;

    // getters & setters

    public Long getId() { return id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Long getChallengeId() { return challengeId; }
    public void setChallengeId(Long challengeId) { this.challengeId = challengeId; }

    public boolean isFinished() { return finished; }
    public void setFinished(boolean finished) { this.finished = finished; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}