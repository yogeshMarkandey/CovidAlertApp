package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class HospitalDataResponse {

    @SerializedName("data")
    private DataForHospitals data;

    public DataForHospitals getData() {
        return data;
    }
}
