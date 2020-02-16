package io.github.nandandesai.linkedin4j.models;

import java.util.Date;

public class Certification {
    private String name;
    private String authority;
    private Date startDate;
    private Date endDate;
    private String url;

    public Certification(String name, String authority, Date startDate, Date endDate, String url) {
        this.name = name;
        this.authority = authority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getAuthority() {
        return authority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getUrl() {
        return url;
    }
}
