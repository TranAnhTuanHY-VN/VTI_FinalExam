package com.entity;

public class Employee extends User {
    private int projectID;
    private String proSkill;

    public Employee(int ID, String fullName, String email, String password, Role role, int projectID, String proSkill) {
        super(ID, fullName, email, password, role);
        this.projectID = projectID;
        this.proSkill = proSkill;
    }

    public String getProSkill() {
        return proSkill;
    }

    public int getProjectID() {
        return projectID;
    }

    @Override
    public String toString() {
        return "|| ProjectID=" + projectID +
                "|| ProSkill='" + proSkill + '\'' +
                '}';
    }
}
