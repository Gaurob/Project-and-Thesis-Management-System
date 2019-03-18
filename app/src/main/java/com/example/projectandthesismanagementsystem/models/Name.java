package com.example.projectandthesismanagementsystem.models;

import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("name")
    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
