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

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;

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

public int getXPosition() {
    return xPosition;
}

public void setXPosition(int xPosition) {
    this.xPosition = xPosition;
}

public int getYPosition() {
    return yPosition;
}

public void setYPosition(int yPosition) {
    this.yPosition = yPosition;
}

public int getWidth() {
    return width;
}

public int getHeight() {
    return height;
}

public void setHeight(int height) {
    this.height = height;
}

public void setWidth(int width) {
    this.width = width;
}

public Visionboard getVisionboard() {
    return visionboard;
}

public void setVisionboard(Visionboard visionboard) {
    this.visionboard = visionboard;
}

  
}