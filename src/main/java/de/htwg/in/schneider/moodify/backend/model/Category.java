package de.htwg.in.schneider.moodify.backend.model;

public enum Category {


    ABLENKUNG("Ablenkung"),
    MOTIVATION("Motivation"),
    ENTSPANNUNG("Entspannung"),
    FOKUS("Fokus");


    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

