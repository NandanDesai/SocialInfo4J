package io.github.nandandesai.linkedin4j.models;

import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private ArrayList<String> members;

    public Project(String title, String description, Date startDate, Date endDate, ArrayList<String> members) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ArrayList<String> getMembers() {
        return members;
    }
}
