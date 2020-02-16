package io.github.nandandesai.linkedin4j.models;

import java.util.Date;

public class VolunteerExperience {
    private String companyName;
    private String role;
    private Date startDate;
    private Date endDate;
    private String cause;
    private String description;

    public VolunteerExperience(String companyName, String role, Date startDate, Date endDate, String cause, String description) {
        this.companyName = companyName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cause = cause;
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getRole() {
        return role;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCause() {
        return cause;
    }

    public String getDescription() {
        return description;
    }
}
