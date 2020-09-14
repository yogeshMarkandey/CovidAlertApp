package com.example.covidalertapp.model;

import com.google.gson.annotations.SerializedName;


public class SampleTestingResponse {


    @SerializedName("data")
    private DataForSampleTested data ;

    public DataForSampleTested getData() {
        return data;
    }
}


