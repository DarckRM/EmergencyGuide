package com.emergencyguide.Entity;

public class PersonalLogo {
    private int id;
    private String basicLogo;
    private String subLogo;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBasicLogo() {
        return basicLogo;
    }

    public void setBasicLogo(String basicLogo) {
        this.basicLogo = basicLogo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubLogo() {
        return subLogo;
    }

    public void setSubLogo(String subLogo) {
        this.subLogo = subLogo;
    }

    @Override
    public String toString() {
        return "PersonalLogo{" +
                "id=" + id +
                ", basicLogo='" + basicLogo + '\'' +
                ", subLogo='" + subLogo + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
