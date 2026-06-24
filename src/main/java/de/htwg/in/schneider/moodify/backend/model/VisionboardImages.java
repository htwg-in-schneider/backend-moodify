package de.htwg.in.schneider.moodify.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class VisionboardImages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "visionboard_id")
    @JsonIgnoreProperties("images")
    private Visionboard visionboard;

    public VisionboardImages() {
    }


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getImageUrl() {
    return imageUrl;
}

public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
}


public Visionboard getVisionboard() {
    return visionboard;
}

public void setVisionboard(Visionboard visionboard) {
    this.visionboard = visionboard;
}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisionboardImages visionboardIMages = (VisionboardImages) o;
        return ID != null && id.equals(visionboard.id);
    }

    @Override
    public int hashCode() {
        return ID != null ? id.hashCode() : 0;
    }


@Override
public String toString() {

    return "Visionboard{" +
            "id=" + id +
            ", imageURL='" + imageURL + '\'' +
            ", visionboard=" + visionboard +
            '}';
}

    
}