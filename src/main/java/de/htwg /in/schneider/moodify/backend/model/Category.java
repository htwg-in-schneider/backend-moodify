package de.htwg.in.schneider.moodify.backend.model;

public enum Category {

    GESUNDHEIT("Gesundheit"),
    KONZENTRATION("Konzentration"),
    ABLENKUNG("Ablenkung"),
    MOTIVATION("Motivation"),
    ENTSPANNUNG("Entspannung"),
    ZIELE("Ziele"),
    KARRIERE("Karriere"),
    SONSTIGE("Sonstige"),
    REISE("Reise"),
    FOKUS("Fokus");


    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

