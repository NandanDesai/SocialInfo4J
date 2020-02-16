package io.github.nandandesai.linkedin4j.models;

import java.util.ArrayList;
import java.util.Date;

public class Publication {
    private String name;
    private String publisher;
    private String description;
    private String url;
    private Date date;
    private ArrayList<String> authors;

    public Publication(String name, String publisher, String description, String url, Date date, ArrayList<String> authors) {
        this.name = name;
        this.publisher = publisher;
        this.description = description;
        this.url = url;
        this.date = date;
        this.authors = authors;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }
}
