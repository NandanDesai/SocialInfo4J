package io.github.nandandesai.linkedin4j.models;

import java.util.Date;

public class Honor {
    private String title;
    private String description;
    private String issuer;
    private Date issueDate;

    public Honor(String title, String description, String issuer, Date issueDate) {
        this.title = title;
        this.description = description;
        this.issuer = issuer;
        this.issueDate = issueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIssuer() {
        return issuer;
    }

    public Date getIssueDate() {
        return issueDate;
    }
}
