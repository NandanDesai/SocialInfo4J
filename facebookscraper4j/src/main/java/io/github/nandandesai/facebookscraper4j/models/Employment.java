package io.github.nandandesai.facebookscraper4j.models;

import java.util.Date;

public class Employment {
    private String companyName;
    private String role;
    private Date startDate;
    private Date endDate;

    public Employment(String companyName, String role, Date startDate, Date endDate) {
        this.companyName = companyName;
        this.role = role;
        this.startDate = startDate;
        this.endDate = endDate;
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
}
