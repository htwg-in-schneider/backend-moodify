package de.htwg.in.schneider.moodify.backend.model;

public enum Schwierigkeitsgrad {

    EASY("easy"),
    MITTEL("mittel"),
    SCHWER("schwer");


    private final String name;

    Schwierigkeitsgrad(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}