package com.example.projectandthesismanagementsystem.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("user_id")
    private int UserId;
    @SerializedName("name")
    private String UserName;
    @SerializedName("user")
    private String User;
    @SerializedName("email")
    private String Email;
    @SerializedName("response")
    private String Response;
    @SerializedName("institution")
    private String Institution;
    @SerializedName("reg")
    private String Reg;
    @SerializedName("cell")
    private String Cell;
    @SerializedName("department")
    private String Department;
    @SerializedName("projects")
    List<Project> Projects;

    public List<Project> getProjects() {
        return Projects;
    }

    public void setProjects(List<Project> projects) {
        Projects = projects;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCell() {
        return Cell;
    }

    public void setCell(String cell) {
        Cell = cell;
    }

    public String getReg() {
        return Reg;
    }

    public void setReg(String reg) {
        Reg = reg;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }
}
