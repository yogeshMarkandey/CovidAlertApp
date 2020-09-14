package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class HospitalRegionalData {

    /*
    *
    * "state": "Andhra Pradesh",
        "ruralHospitals": 193,
        "ruralBeds": 6480,
        "urbanHospitals": 65,
        "urbanBeds": 16658,
        "totalHospitals": 258,
        "totalBeds": 23138,
        "asOn": "2017-01-01T00:00:00.000Z
     *
     * */


    @SerializedName("state")
    private String state;
    @SerializedName("ruralHospitals")
    private String ruralHospital;
    @SerializedName("ruralBeds")
    private String ruralBeds;
    @SerializedName("urbanHospitals")
    private String urbanHospitals;
    @SerializedName("urbanBeds")
    private String urbanBeds;
    @SerializedName("totalHospitals")
    private String totalHospitals;
    @SerializedName("totalBeds")
    private String totalBeds;
    @SerializedName("asOn")
    private String asOn;


    public String getState() {
        return state;
    }

    public String getRuralHospital() {
        return ruralHospital;
    }

    public String getRuralBeds() {
        return ruralBeds;
    }

    public String getUrbanHospitals() {
        return urbanHospitals;
    }

    public String getUrbanBeds() {
        return urbanBeds;
    }

    public String getTotalHospitals() {
        return totalHospitals;
    }

    public String getTotalBeds() {
        return totalBeds;
    }

    public String getAsOn() {
        return asOn;
    }
}
