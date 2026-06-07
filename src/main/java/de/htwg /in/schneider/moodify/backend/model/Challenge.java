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
    private Long id;
    private String title;
    private String description;
    private String schwierigkeitsgrad;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Challenge() {
    }

    public Challenge(Long id, String title, String description, String schwierigkeitsgrad, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
        this.category = category;
    }


    public Long getid() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setid(Long id) {
        this.id = id;
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
        return id != null && id.equals(challenge.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {

        return "Challenge{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", schwierigkeitsgrad='" + schwierigkeitsgrad + '\'' +
            ", category=" + category +
            '}';
    }

}