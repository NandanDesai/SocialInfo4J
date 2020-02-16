package io.github.nandandesai.facebookscraper4j.models;

public class FamilyMember {
    private String username;
    private String relation;


    public FamilyMember(String username, String relation) {
        this.username = username;
        this.relation = relation;
    }

    public String getUsername() {
        return username;
    }

    public String getRelation() {
        return relation;
    }
}
