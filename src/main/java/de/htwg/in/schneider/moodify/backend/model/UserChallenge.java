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

    

    public Long getId() { 
        return id; 
    }

    public String getUserId() { 
        return userId; 
    }

    public void setUserId(String userId) { 
        this.userId = userId; 
    }

    public Long getChallengeId() { 
        return challengeId; 
    }

    public void setChallengeId(Long challengeId) { 
        this.challengeId = challengeId; 
    }

    public boolean isFinished() { 
        return finished; 
    }

    public void setFinished(boolean finished) { 
        this.finished = finished; 
    }

    public String getMood() { 
        return mood; 
    }

    public void setMood(String mood) { 
        this.mood = mood; 
    }

    public String getReview() { 
        return review; 
    }

    public void setReview(String review) { 
        this.review = review; 
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChallenge userchallenge = (UserChallenge) o;
        return id != null && id.equals(userchallenge.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "UserChallenge{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", challengeId='" + challengeId + '\'' +
            ", finished='" + finished + '\'' +
            ", mood='" + mood + '\'' +
            ", review=" + review +
            '}';
    }
}