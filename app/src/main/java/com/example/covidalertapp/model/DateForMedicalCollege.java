package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DateForMedicalCollege {

    @SerializedName("medicalColleges")
    List<MedicalCollegesObject> collegesList;

    public List<MedicalCollegesObject> getCollegesList() {
        return collegesList;
    }
}
