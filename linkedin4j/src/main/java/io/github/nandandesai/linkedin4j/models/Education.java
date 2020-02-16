package io.github.nandandesai.linkedin4j.models;

import java.util.Date;

public class Education {
    private String schoolName;
    private String degreeName;
    private String activities;
    private String grade;
    private Date startDate;
    private Date endDate;
    private String fieldOfStudy;

    public Education(String schoolName, String degreeName, String activities, String grade, Date startDate, Date endDate, String fieldOfStudy) {
        this.schoolName = schoolName;
        this.degreeName = degreeName;
        this.activities = activities;
        this.grade = grade;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public String getActivities() {
        return activities;
    }

    public String getGrade() {
        return grade;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }
}
