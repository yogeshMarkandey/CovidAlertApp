package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataForHospitals {

    @SerializedName("summary")
    private HospitalSummary summary;

    @SerializedName("regional")
    private List<HospitalRegionalData> regionalDataList;

    public HospitalSummary getSummary() {
        return summary;
    }

    public List<HospitalRegionalData> getRegionalDataList() {
        return regionalDataList;
    }
}
