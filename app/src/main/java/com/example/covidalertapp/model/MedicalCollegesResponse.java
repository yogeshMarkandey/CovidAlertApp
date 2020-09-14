package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class MedicalCollegesResponse {

    @SerializedName("data")
    DateForMedicalCollege data;

    public DateForMedicalCollege getData() {
        return data;
    }
}
