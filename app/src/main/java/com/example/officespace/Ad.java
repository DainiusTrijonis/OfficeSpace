package com.example.officespace;

import java.io.Serializable;

public class Ad implements Serializable {
    private String companyName, location, ownerID, salary, title, about,imageUri;
    public Ad() {
    }

    public Ad(String companyName, String imageUri, String location, String ownerID, String salary, String title, String about) {
        this.companyName = companyName;
        this.imageUri = imageUri;
        this.location = location;
        this.ownerID = ownerID;
        this.salary = salary;
        this.title = title;
        this.about = about;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getLocation() {
        return location;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public String getAbout() {
        return about;
    }
}
