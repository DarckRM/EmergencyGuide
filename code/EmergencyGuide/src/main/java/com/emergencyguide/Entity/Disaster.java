package com.emergencyguide.Entity;

public class Disaster {
    private int id;
    private String disasterType;
    private String solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Disaster{" +
                "id=" + id +
                ", disasterType='" + disasterType + '\'' +
                ", solution='" + solution + '\'' +
                '}';
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
