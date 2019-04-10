package com.example.projectandthesismanagementsystem.models;

import com.google.gson.annotations.SerializedName;

public class Project {
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
    private Boolean First_seg;
    @SerializedName("second_seg")
    private Boolean Seconde_Seg;
    @SerializedName("third_seg")
    private Boolean Third_Seg;
    @SerializedName("accepted")
    private Boolean Accepted;

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

    public Boolean getFirst_seg() {
        return First_seg;
    }

    public void setFirst_seg(Boolean first_seg) {
        First_seg = first_seg;
    }

    public Boolean getSeconde_Seg() {
        return Seconde_Seg;
    }

    public void setSeconde_Seg(Boolean seconde_Seg) {
        Seconde_Seg = seconde_Seg;
    }

    public Boolean getThird_Seg() {
        return Third_Seg;
    }

    public void setThird_Seg(Boolean third_Seg) {
        Third_Seg = third_Seg;
    }

    public Boolean getAccepted() {
        return Accepted;
    }

    public void setAccepted(Boolean accepted) {
        Accepted = accepted;
    }
}
