package de.htwg.in.schneider.moodify.backend.model;

public enum Difficulty {

    EASY("easy"),
    MITTEL("mittel"),
    SCHWER("schwer");


    private final String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}