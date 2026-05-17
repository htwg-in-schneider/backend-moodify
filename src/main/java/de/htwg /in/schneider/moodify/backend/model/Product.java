package de.htwg.in.schneider.moodify.backend.model;

public class Product {

    private String title;
    private String description;
    private Category category;

    public Product() {
    }

    public Product(String title, String description, Category category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}