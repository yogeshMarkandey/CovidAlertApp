package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

public class HospitalSummary {

    /*"ruralHospitals": 39620,
      "ruralBeds": 559176,
      "urbanHospitals": 7544,
      "urbanBeds": 862346,
      "totalHospitals": 47164,
      "totalBeds": 1421522*/


    @SerializedName("ruralHospitals")
    private String ruralHospitals;
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

    public String getRuralHospitals() {
        return ruralHospitals;
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
}
