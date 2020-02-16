package io.github.nandandesai.facebookscraper4j.models;

import java.util.Date;
import java.util.List;

public class Profile {
    private String username;
    private String name;
    private String currentCity;
    private String hometown;
    private String gender;
    private String relationshipStatus;
    private List<String> languages;
    private Date dateOfBirth;
    private List<Education> educationList;
    private List<Employment> employmentList;
    private List<FamilyMember> familyMembers;

    public Profile(String username, String name, String currentCity, String hometown, String gender, String relationshipStatus, List<String> languages, Date dateOfBirth, List<Education> educationList, List<Employment> employmentList, List<FamilyMember> familyMembers) {
        this.username = username;
        this.name = name;
        this.currentCity = currentCity;
        this.hometown = hometown;
        this.gender = gender;
        this.relationshipStatus = relationshipStatus;
        this.languages = languages;
        this.dateOfBirth = dateOfBirth;
        this.educationList = educationList;
        this.employmentList = employmentList;
        this.familyMembers=familyMembers;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public String getHometown() {
        return hometown;
    }

    public String getGender() {
        return gender;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<Employment> getEmploymentList() {
        return employmentList;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public List<FamilyMember> getFamilyMembers() {
        return familyMembers;
    }
}
