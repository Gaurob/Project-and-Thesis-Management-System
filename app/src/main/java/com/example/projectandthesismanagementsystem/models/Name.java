package com.example.projectandthesismanagementsystem.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Name {
    @SerializedName("name")
    String Name;
    @SerializedName("t_id")
    int TeacherID;


    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int teacherID) {
        TeacherID = teacherID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
