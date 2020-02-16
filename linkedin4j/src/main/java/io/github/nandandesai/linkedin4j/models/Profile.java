package io.github.nandandesai.linkedin4j.models;

import java.util.List;

public class Profile {
    private String publicIdentifier;

    private String name;
    private String location;
    private boolean isStudent;
    private String industry;
    private String summary;
    private String headline;

    private List<String> skills;
    private List<String> courses;

    private List<Certification> certifications;
    private List<Education> educationList;
    private List<EmploymentPosition> employmentPositions;
    private List<Honor> honors;
    private List<Language> languages;
    private List<Project> projects;
    private List<Publication> publications;
    private List<VolunteerExperience> volunteerExperiences;

    public Profile(String name, String location, boolean isStudent, String industry, String summary, String headline,
                   List<String> skills, List<String> courses,
                   List<Certification> certifications, List<Education> educationList, List<EmploymentPosition> employmentPositions, List<Honor> honors, List<Language> languages, List<Project> projects, List<Publication> publications, List<VolunteerExperience> volunteerExperiences) {
        this.name = name;
        this.location = location;
        this.isStudent = isStudent;
        this.industry = industry;
        this.summary = summary;
        this.headline = headline;
        this.skills = skills;
        this.courses = courses;
        this.certifications = certifications;
        this.educationList = educationList;
        this.employmentPositions = employmentPositions;
        this.honors = honors;
        this.languages = languages;
        this.projects = projects;
        this.publications = publications;
        this.volunteerExperiences = volunteerExperiences;
    }

    public void setPublicIdentifier(String publicIdentifier) {
        this.publicIdentifier = publicIdentifier;
    }

    public String getPublicIdentifier() {
        return publicIdentifier;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public String getIndustry() {
        return industry;
    }

    public String getSummary() {
        return summary;
    }

    public String getHeadline() {
        return headline;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public List<Education> getEducationList() {
        return educationList;
    }

    public List<EmploymentPosition> getEmploymentPositions() {
        return employmentPositions;
    }

    public List<Honor> getHonors() {
        return honors;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public List<VolunteerExperience> getVolunteerExperiences() {
        return volunteerExperiences;
    }
}