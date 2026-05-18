package de.htwg.in.schneider.moodify.backend.model;

public enum Category {

    GESUNDHEIT("Gesundheit"),
    KONZENTRATION("Konzentration"),
    ABLENKUNG("Ablenkung"),
    ENTSPANNUNG("Entspannung");


    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

