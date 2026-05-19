package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ElementCollection;

import java.time.LocalDateTime;
import java.util.Arrays;
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

    @ElementCollection
    private List<String> imageUrls = new ArrayList<>();

    public Visionboard() {
    }

    public Visionboard(Long ID, String title, LocalDateTime CreatedAt, Category category, List<String> imageUrls) {
        this.ID = ID;
        this.title = title;
        this.CreatedAt = CreatedAt;
        this.category = category;
        this.imageUrls = imageUrls;
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

    public List<String> getImageUrls(){
        return imageUrls;
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

    public void setImageUrls(List<String> imageUrls){
        this.imageUrls = imageUrls;
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

        return "Product{" +
            "ID=" + ID +
            ", title='" + title + '\'' +
            ", created at='" + CreatedAt + '\'' +
            ", category=" + category + '\'' +
            ", imageUrls=" + imageUrls +
            '}';
    }

}