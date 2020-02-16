package io.github.nandandesai.linkedin4j.models;

import java.util.ArrayList;
import java.util.Date;

public class EmploymentPosition {
    private String companyName;
    private ArrayList<Position> positions;

    public EmploymentPosition(String companyName, ArrayList<Position> positions) {
        this.companyName = companyName;
        this.positions = positions;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public String getCompanyName() {
        return companyName;
    }


    public static class Position{
        private String location;
        private String title;
        private String description;
        private Date startDate;
        private Date endDate;

        public Position(String location, String title, String description, Date startDate, Date endDate) {
            this.location = location;
            this.title = title;
            this.description = description;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public String getLocation() {
            return location;
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
    }
}
