package io.github.nandandesai.linkedin4j.models;

public class Language {
    private String name;
    private String proficiency;

    public Language(String name, String proficiency) {
        this.name = name;
        this.proficiency = proficiency;
    }

    public String getName() {
        return name;
    }

    public String getProficiency() {
        return proficiency;
    }
}
