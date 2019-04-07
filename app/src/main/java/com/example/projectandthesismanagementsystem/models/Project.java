package com.example.projectandthesismanagementsystem.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Project implements Serializable {

    @SerializedName("project_id")
    private int Project_id;
    @SerializedName("teacher_id")
    private int Teacher_id;
    @SerializedName("title")
    private String Title;
    @SerializedName("description")
    private String Description;
    @SerializedName("stu_name")
    private String StudentName;
    @SerializedName("adviser_name")
    private String Adviser_name;
    @SerializedName("platform")
    private String Platform;
    @SerializedName("technology")
    private String Technology;
    @SerializedName("github")
    private String Github;
    @SerializedName("first_seg")
    private int First_seg;
    @SerializedName("second_seg")
    private int Seconde_Seg;
    @SerializedName("third_seg")
    private int Third_Seg;
    @SerializedName("accepted")
    private int Accepted;

    public int getTeacher_id() {
        return Teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        Teacher_id = teacher_id;
    }


    public int getProject_id() {
        return Project_id;
    }

    public void setProject_id(int project_id) {
        Project_id = project_id;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    @SerializedName("comment")
    private String Comment;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getAdviser_name() {
        return Adviser_name;
    }

    public void setAdviser_name(String adviser_name) {
        Adviser_name = adviser_name;
    }

    public String getPlatform() {
        return Platform;
    }

    public void setPlatform(String platform) {
        Platform = platform;
    }

    public String getTechnology() {
        return Technology;
    }

    public void setTechnology(String technology) {
        Technology = technology;
    }

    public String getGithub() {
        return Github;
    }

    public void setGithub(String github) {
        Github = github;
    }

    public int getFirst_seg() {
        return First_seg;
    }

    public void setFirst_seg(int first_seg) {
        First_seg = first_seg;
    }

    public int getSeconde_Seg() {
        return Seconde_Seg;
    }

    public void setSeconde_Seg(int seconde_Seg) {
        Seconde_Seg = seconde_Seg;
    }

    public int getThird_Seg() {
        return Third_Seg;
    }

    public void setThird_Seg(int third_Seg) {
        Third_Seg = third_Seg;
    }

    public int getAccepted() {
        return Accepted;
    }

    public void setAccepted(int accepted) {
        Accepted = accepted;
    }
}
