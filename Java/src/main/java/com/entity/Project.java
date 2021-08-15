package com.entity;

public class Project {
    private int projectID;
    private int teamSize;
    private int idManager;
    private int idEmployee;

    public Project(int projectID, int teamSize, int idManager, int idEmployee) {
        this.projectID = projectID;
        this.teamSize = teamSize;
        this.idManager = idManager;
        this.idEmployee = idEmployee;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID +
                ", teamSize=" + teamSize +
                ", idManager=" + idManager +
                ", idEmployee=" + idEmployee +
                '}';
    }
}
