package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class MedicalCollegesObject {

    /*"state": "A & N Islands",
        "name": "Andaman & Nicobar Islands Insitute of Medical Sciences, Port Blair",
        "city": "Port Blair",
        "ownership": "Govt.",
        "admissionCapacity": 100,
        "hospitalBeds": 460*/

    @SerializedName("state")
    private String state;
    @SerializedName("name")
    private String name;
    @SerializedName("city")
    private String city;
    @SerializedName("ownership")
    private String ownership;
    @SerializedName("admissionCapacity")
    private String admissionCapacity;
    @SerializedName("hospitalBeds")
    private String hospitalBeds;

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getOwnership() {
        return ownership;
    }

    public String getAdmissionCapacity() {
        return admissionCapacity;
    }

    public String getHospitalBeds() {
        return hospitalBeds;
    }
}
