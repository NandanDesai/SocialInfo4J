package io.github.nandandesai.facebookscraper4j.models;

import java.util.Date;

public class Photo {
    private String photoUrl;
    private Date uploadedDate;

    public Photo(String photoUrl, Date uploadedDate) {
        this.photoUrl = photoUrl;
        this.uploadedDate = uploadedDate;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Date getUploadedDate() {
        return uploadedDate;
    }
}
