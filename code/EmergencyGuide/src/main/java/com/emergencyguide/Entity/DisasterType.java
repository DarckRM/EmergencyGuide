package com.emergencyguide.Entity;

public class DisasterType {
    private int id;
    private String disasterType;
    private int disasterNumber;
    private String solution;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public int getDisasterNumber() {
        return disasterNumber;
    }

    public void setDisasterNumber(int disasterNumber) {
        this.disasterNumber = disasterNumber;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
