package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String title;
    private String description;
    private String schwierigkeitsgrad;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Challenge() {
    }

    public Challenge(Long ID, String title, String description, String schwierigkeitsgrad, Category category) {
        this.ID = ID;
        this.title = title;
        this.description = description;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.category = category;
    }


    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public void setSchwierigkeitsgrad(String schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Challenge challenge = (Challenge) o;
        return ID != null && ID.equals(challenge.ID);
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "Challenge{" +
            "ID=" + ID +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", schwierigkeitsgrad='" + schwierigkeitsgrad + '\'' +
            ", category=" + category +
            '}';
    }

}