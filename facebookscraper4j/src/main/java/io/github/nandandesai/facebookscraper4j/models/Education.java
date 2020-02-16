package io.github.nandandesai.facebookscraper4j.models;

import java.util.List;

public class Education {
    private String schoolName;
    private String schoolType;//School, High School, University etc.
    private List<String> classmates;

    public Education(String schoolName, String schoolType, List<String> classmates) {
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        this.classmates=classmates;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public List<String> getClassmates() {
        return classmates;
    }
}
