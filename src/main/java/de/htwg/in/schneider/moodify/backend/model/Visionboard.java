package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Visionboard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String title;
    private LocalDateTime CreatedAt;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password"})
    private User user;
    
    @OneToMany(mappedBy = "visionboard", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("visionboard")
    private List<VisionboardImages> images = new ArrayList<>();

    public Visionboard() {
    }

    public Visionboard(Long ID, String title, LocalDateTime CreatedAt, Category category, List<VisionboardImages> images) {
    this.ID = ID;
    this.title = title;
    this.CreatedAt = CreatedAt;
    this.category = category;
    this.images = images;
    this.user = user;
    }


    public Long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public List<VisionboardImages> getImages() {
        return images;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedAt(LocalDateTime CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public void setImages(List<VisionboardImages> images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visionboard visionboard = (Visionboard) o;
        return ID != null && ID.equals(visionboard.ID);
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }


@Override
public String toString() {

    return "Visionboard{" +
            "ID=" + ID +
            ", title='" + title + '\'' +
            ", createdAt=" + CreatedAt +
            ", category=" + category +
            ", images=" + images +
            '}';
}

}