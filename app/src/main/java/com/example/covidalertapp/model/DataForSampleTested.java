package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataForSampleTested {

    @SerializedName("data")
    private List<DailyTestReport> listOfReport;

    public List<DailyTestReport> getListOfReport() {
        return listOfReport;
    }
}
